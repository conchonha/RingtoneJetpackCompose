package com.example.myapplication.data.data_source.config

import android.app.Application
import android.text.TextUtils
import com.example.myapplication.utils.Validator
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.HashMap
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton
import com.example.myapplication.BuildConfig

/**
 * Interceptor: is a very useful feature, it helps us to edit the request / response, retry the call, monitor the application.
 * + Application Interceptor: this type of interceptor is usually used to change the title/query for both
 * + request/ response -> make sure to call once even with ccache
 * + Network Interceptor: used to monitor the request as it is transmitted over the network. -> do not call if retrieved
 * from cache
 */
class LoggingInterceptor @Inject constructor(
    private val application: Application,
    private val httpLoggingInterceptor: HttpLoggingInterceptor,
    private val builder : Builder
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var requestBuilder : Request.Builder

        /**
         * HttpLoggingInterceptor we can print out all logs for HTTP activities
         * through the client as follows:
         * + Level.BASIC : log the request/response lines
         * + Level.BODY: log the lines, and the corresponding header and body (if any)
         * + Level.HEADERS: logs out the corresponding header lines
         * + Level.NONE : nothing is logged
         * -> only print log in debug environment
         */
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.intercept(chain)
        }

        if (Validator.isInternetAvailable(context = application)) {
            //request from save cache 5 millisecond
            requestBuilder = request.newBuilder().header("Cache-Control", "public, max-age=" + 5)
        } else {
            // request from save cache 1 day
          return request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1)
                .build().let { chain.proceed(it) }
        }

        if(builder.getHeaders().size > 0){
            builder.getHeaders().keys.forEach {key->
                builder.getHeaders()[key]?.let { value -> requestBuilder.addHeader(key, value) }
            }
            request = requestBuilder.build()
        }

        val queryMap: HashMap<String, String> = builder.getHttpUrl()
        if (queryMap.size > 0) {
            val httpUrlBuilder: HttpUrl.Builder? = request.url.newBuilder(request.url.toString())
            for (o in queryMap.keys) {
                val value = queryMap[o]
                assert(httpUrlBuilder != null)
                httpUrlBuilder?.addQueryParameter(o, value)
            }
            request = request.newBuilder().url(httpUrlBuilder!!.build()).build()
        }

        return chain.proceed(request)
    }

    @Singleton
    class Builder {
        private var tag = this.javaClass.name
        private val headers: HashMap<String, String> = HashMap()
        private val httpUrl: HashMap<String, String> = HashMap()
        private var logHackEnable = false
        private var requestTag: String? = null
        private var responseTag: String? = null
        private var executor: Executor? = null
        private var mockEnabled = false
        private var sleepMs: Long = 0

        fun isLogHackEnable(): Boolean = logHackEnable

        fun getHeaders(): HashMap<String, String> = headers

        fun getHttpUrl(): HashMap<String, String> = httpUrl

        fun getTag(isRequest: Boolean): String {
            return if (isRequest) {
                if (TextUtils.isEmpty(requestTag)) tag else requestTag!!
            } else {
                if (TextUtils.isEmpty(responseTag)) tag else responseTag!!
            }
        }

        fun addHeader(name: String, value: String): Builder = apply { headers[name] = value }

        fun addQueryParam(name: String, value: String): Builder = apply { httpUrl[name] = value }

        fun tag(tag: String): Builder = apply { this.tag = tag }

        fun request(tag: String?): Builder = apply { requestTag = tag }

        fun response(tag: String?): Builder = apply { responseTag = tag }

        fun executor(executor: Executor?): Builder = apply { this.executor = executor }

        fun getExecutor(): Executor? = executor

        fun isEnableMock() = mockEnabled

        fun getSleepMs() = sleepMs

        fun enableAndroidStudioV3LogsHack(useHack: Boolean): Builder =
            apply { logHackEnable = useHack }
    }
}


