package com.bootcamp.nttdata.data.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun getRetrofit(
        baseUrl: String?,
        timeout: Long?,
        header: Map<String, String>?,
    ): Retrofit? {
        baseUrl?.let {
            val retrofitBuilder = Retrofit.Builder().baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())

            retrofitBuilder.client(getOkHttpClient(timeout, header))
            return retrofitBuilder.build()
        }
        return null
    }

    private fun getOkHttpClient(
        timeout: Long? = 1,
        header: Map<String, String>?,
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()


        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder.connectTimeout(timeout!!, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(timeout!!, TimeUnit.MINUTES)

        val header = header

        if (header != null && header.isNotEmpty()) {
            okHttpClientBuilder.addInterceptor(getInterceptor(header))
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

        return okHttpClientBuilder.build()
    }

    private fun getInterceptor(header: Map<String, String>?): Interceptor {

        return Interceptor { chain ->
            var request = chain.request()

            if (header != null && header.isNotEmpty()) {
                for ((key, value) in header) {
                    request = request.newBuilder().addHeader(key, value).build()
                }
            }
            val originalResponse = chain.proceed(request)
            originalResponse.newBuilder().build()
        }
    }
}