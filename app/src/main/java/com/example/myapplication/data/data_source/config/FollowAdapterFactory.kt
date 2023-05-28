package com.example.myapplication.data.data_source.config

import android.app.Application
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class FollowAdapterFactory @Inject constructor(
    private val builder: Builder,
    private val application: Application
) : CallAdapter.Factory() {
    private val handleException = HandleException.getInstance(application)

    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawType: Class<*> = getRawType(returnType)
        //just accept type is flow
        if (rawType != Flow::class.java) return null

        // not is Parameterized (object/collection)
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "ResponseAPI<T> return type must be parameterized" + " as " + "ResponseAPI<T><Foo> or " + "ResponseAPI<T><? extends Foo>"
            )
        }

        val innerType: Type = getParameterUpperBound(0, returnType)
        return CallAdapterWithResponseAPI<Any?>(innerType)
    }

    private inner class CallAdapterWithResponseAPI<T>(private val responseType: Type) :
        CallAdapter<T, Flow<ResponseAPI<T>>> {
        override fun responseType() = responseType

        override fun adapt(call: Call<T>) = flow {
            emit(suspendCancellableCoroutine { continuation ->
                //auto cancel when continuation. It is completed when resumed or cancelled, synchrony
                continuation.invokeOnCancellation {
                    call.cancel()
                }
                call.enqueue(FlowCallback(handleException, builder, continuation))
            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    class FlowCallback<T>(
        private val handleException: HandleException,
        private val builder: Builder,
        private val continuation: CancellableContinuation<ResponseAPI<T>>
    ) : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            handleApiResponse(response, continuation)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            handleException.handleNetworkException(t).let {
                continuation.resume(
                    ResponseAPI.NetworkException(
                        it?.first.toString(),
                        it?.second ?: ExceptionType.TYPE_DEFAULT
                    )
                )
            }
        }

        //demo test = func sau ddos tach ra class rieng
        private fun <T> handleApiResponse(
            response: Response<T>, continuation: CancellableContinuation<ResponseAPI<T>>
        ) {
            if (response.isSuccessful.not()) {
                handleException.handleExceptionNetwork(response.code()).let {
                    continuation.resume(
                        ResponseAPI.NetworkException(
                            it?.first.toString(),
                            it?.second ?: ExceptionType.TYPE_DEFAULT
                        )
                    )
                }
            }

            if (response.body() == null) {
                continuation.resume(
                    ResponseAPI.NetworkException(
                        "Exception nothing response data ",
                        ExceptionType.TYPE_NULL_THROWN_ERROR
                    )
                )
            }

            (builder.response?.invoke(response) as? ResponseAPI<T>)?.let {
                continuation.resume(it)
            } ?: continuation.resume(ResponseAPI.Success(response.body()!!))
        }
    }

    class Builder {
        private var tag = this.javaClass.name
        internal var response: (Response<*>.() -> ResponseAPI<*>)? = null

        fun setTag(tag1: String) = apply {
            tag = tag1
        }

        fun handleResponseFromServe(call: Response<*>.() -> ResponseAPI<*>) = apply {
            response = call
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