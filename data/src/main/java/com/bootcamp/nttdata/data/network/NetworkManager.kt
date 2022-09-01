package com.bootcamp.nttdata.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkManager private constructor(
    private var baseUrl: String?,
    private var timeout: Long? = 1,
    private var endpoint: String = "",
    private var type: Int = 0,
    private var body: Any? = null,
    private var header: Map<String, String>? = null,
) {

    data class Builder(
        private var baseUrl: String? = null,
        private var timeout: Long? = 5,
        private var endpoint: String? = null,
        private var type: Int? = null,
        private var body: Any? = null,
        private var header: Map<String, String>? = null,
    ) {

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }
        fun timeout(minutes: Long?) = apply { this.timeout = minutes }
        fun endpoint(endpoint: String?) = apply { this.endpoint = endpoint }
        fun type(type: Int?) = apply { this.type = type }
        fun body(body: Any?) = apply { this.body = body }
        fun header(header: Map<String, String>?) = apply { this.header = header }

        fun build() = NetworkManager(
            this.baseUrl ?: "",
            this.timeout ?: 5,
            this.endpoint ?: error("Endpoint requerido"),
            this.type ?: 0,
            this.body,
            this.header ?: emptyMap()
        )

    }

    companion object {
        const val GET = 0
        const val POST = 1
        const val PUT = 2
        const val DELETE = 3
        const val PATCH = 4
        const val POST_FORM = 5
        const val PUT_FORM = 6
        const val PATCH_FORM = 7
    }

    private fun getRetrofit(): Retrofit? {
        baseUrl?.let {
            val retrofitBuilder = Retrofit.Builder().baseUrl(it)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())

            retrofitBuilder.client(getOkHttpClient())
            return retrofitBuilder.build()
        }
        return null
    }

    private fun getOkHttpClient(
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        /*
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
            */

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


    suspend fun execute(): Response<Any>? {
        getRetrofit()?.let {
            return when (type) {
                GET ->
                    it.create(IService::class.java).get(endpoint).await()
                POST -> it.create(IService::class.java).post(endpoint, body).await()
                PUT -> it.create(IService::class.java).put(endpoint, body).await()
                DELETE -> it.create(IService::class.java).delete(endpoint).await()
                PATCH -> it.create(IService::class.java).patch(endpoint, body).await()
                POST_FORM -> it.create(IService::class.java)
                    .postEncode(endpoint, getBodyFormUrlEncoded()).await()
                PUT_FORM -> it.create(IService::class.java)
                    .putEncode(endpoint, getBodyFormUrlEncoded()).await()
                PATCH_FORM -> it.create(IService::class.java)
                    .patchEncode(endpoint, getBodyFormUrlEncoded()).await()
                else -> it.create(IService::class.java).get(endpoint).await()
            }
        }
        return null
    }

    private fun getBodyFormUrlEncoded(): HashMap<String, String> {
        val data: HashMap<String, String> = HashMap()
        if (body is HashMap<*, *>
        ) {
            val body = body as? HashMap<*, *>

            body?.let {
                for (line in body.keys) {
                    val key = line as String
                    val value = body[line] as String
                    data[key] = value
                }
            }
        }
        return data
    }

}