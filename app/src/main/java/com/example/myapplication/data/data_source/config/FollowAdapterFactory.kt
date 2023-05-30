package com.example.myapplication.data.data_source.config

import android.util.Log
import com.example.myapplication.utils.DialogUtils
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.coroutines.resume

class FlowAdapterFactory : CallAdapter.Factory() {
    private val handleException = HandleException.getInstance()
    /*
    * returnType: kiểu dữ liệu trả về của phương thức trong interface
    * annotations:  Mảng các annotations được áp dụng cho phương thức gọi trong Retrofit service interface
    * */
    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType: Class<*> = getRawType(returnType)
        //just accept type is flow
        if (rawType != Flow::class.java) return null

        val responseType = getParameterUpperBound(0, returnType as ParameterizedType) //ResponseApi<Lit<T>>
        val innerType = getParameterUpperBound(0, responseType as ParameterizedType) // List<T>
        return CallAdapterWithResponseAPI<ResponseAPI<*>>(innerType = innerType)
    }

    private inner class CallAdapterWithResponseAPI<T>(val innerType: Type) : CallAdapter<T, Flow<ResponseAPI<T>>> {
        override fun responseType() = innerType //innerType type để gson convert from response.body từ server

        override fun adapt(call: Call<T>) : Flow<ResponseAPI<T>> = flow {
            DialogUtils.showLoading()
            emit(suspendCancellableCoroutine { continuation ->
                //auto cancel when continuation. It is completed when resumed or cancelled, synchrony
                continuation.invokeOnCancellation {
                    DialogUtils.hideLoading()
                    call.cancel()
                }
                call.enqueue(FlowCallback(continuation))
            })

        }
    }

    @Suppress("UNCHECKED_CAST")
    private class FlowCallback<T>(
        private  val continuation: CancellableContinuation<ResponseAPI<T>>
    ) : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            handleApiResponse(response, continuation)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("AAAA", "onFailure: ${t.message}")
            HandleException.getInstance().handleNetworkException(t).let {
                continuation.resume(ResponseAPI.NetworkException(it?.first ?: t.message.toString(), it?.second ?:  ExceptionType.TYPE_DEFAULT))
            }
        }

        private fun <T> handleApiResponse(
            response: Response<T>, continuation: CancellableContinuation<ResponseAPI<T>>
        ) {
            Log.d("AAAA", "handleApiResponse: ${response.body()}")
            if (response.isSuccessful.not() || response.body() == null) {
                HandleException.getInstance().handleExceptionNetwork(response.code()).let {
                    continuation.resume(
                        ResponseAPI.NetworkException(
                            it?.first.toString(),
                            it?.second ?: ExceptionType.TYPE_DEFAULT
                        ))
                }
            }else{
                continuation.resume(ResponseAPI.Success(response.body() as T))
            }
        }
    }
}

sealed class ResponseAPI<out T> {
    data class Success<out T>(val data: T) : ResponseAPI<T>()

    data class NetworkException(
        val message: String = "",
        val type: ExceptionType = ExceptionType.TYPE_DEFAULT,
        val cause: Throwable = Throwable("")
    ) : ResponseAPI<Nothing>()
}