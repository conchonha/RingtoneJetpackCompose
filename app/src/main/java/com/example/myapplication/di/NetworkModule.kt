package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.data.data_source.ApiService
import com.example.myapplication.data.data_source.config.FlowAdapterFactory
import com.example.myapplication.data.data_source.config.LoggingInterceptor
import com.example.myapplication.data.data_source.config.TLSSocketFactory
import com.example.myapplication.data.repository.RingtoneRepositoryImpl
import com.example.myapplication.domain.repository.RingtoneRepository
import com.example.myapplication.domain.use_case.GetAllCategory
import com.example.myapplication.domain.use_case.RingtoneUserCase
import com.example.myapplication.utils.Const.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    /**
     *@sample providesHttpLoggingInterceptor() by retrofit provider, can register with OkHttp, with this object we can
     * printf all activities of  as:
     *  request/response/header/body/..
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * @sample providesLoggingInterceptorBuilder using addHeader / queryParam for request / response from server
     */
    @Singleton
    @Provides
    fun providesLoggingInterceptorBuilder(): LoggingInterceptor.Builder =
        LoggingInterceptor.Builder()
            .request("Request")
            .response("Response")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")

    /**
     * @sample provideGson
     * init gson using for ConverterFactory retrofit convert json -> object
     */
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * @param cache If the same network resource needs to be accessed again after accessing it recently,
     * the device will not need to make a request to the server; it will response from cached
     * @sample providesOkHttpClient provider OkHttp allow using Http protocol easily
     */
    @Singleton
    @Provides
    fun providesOkHttpClient(
        application: Application,
        loggingInterceptor: LoggingInterceptor
    ): OkHttpClient {
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cacheSize = (10 * 1024 * 1024).toLong() //10mb
        val cache = Cache(cacheDir, cacheSize)

        //socket factory
        val tslFactory = TLSSocketFactory()

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .sslSocketFactory(tslFactory, tslFactory.systemDefaultTrustManager())
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * @sample provideRetrofit return retrofit.builder
     */
    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(FlowAdapterFactory())
    }

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder) =
        builder.baseUrl(BASE_URL).build().create<ApiService>()

    @Provides
    @Singleton
    fun provideRingtoneRepository(apiService: ApiService): RingtoneRepository {
        return RingtoneRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRingtoneUseCase(ringtoneRepository: RingtoneRepository): RingtoneUserCase {
        return RingtoneUserCase(GetAllCategory((ringtoneRepository)))
    }
}