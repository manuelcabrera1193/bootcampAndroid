package com.bootcamp.nttdata.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject private constructor(
    private val baseUrl: String,
    private val endpoint: String,
    private val type: MethodHttp = MethodHttp.GET,
    private val body: Any? = null,
    private val header: Map<String, String>,
    private val timeout: Long,
) {

    data class Builder(
        private var baseUrl: String? = null,
        private var endpoint: String? = null,
        private var type: MethodHttp? = null,
        private var body: Any? = null,
        private var header: Map<String, String>? = null,
        private var timeout: Long? = 5,
    ) {

        fun baseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
        fun endpoint(endpoint: String) = apply { this.endpoint = endpoint }
        fun type(type: MethodHttp) = apply { this.type = type }
        fun body(body: Any?) = apply { this.body = body }
        fun header(header: Map<String, String>?) = apply { this.header = header }
        fun timeout(minutes: Long) = apply { this.timeout = minutes }

        fun build() = NetworkManager(
            baseUrl = this.baseUrl ?: error("Base requerida"),
            endpoint = this.endpoint ?: error("Endpoint requerido"),
            type = this.type ?: MethodHttp.GET,
            body = this.body,
            header = this.header ?: emptyMap(),
            timeout = this.timeout ?: 5,
        )
    }

    suspend fun execute(): Response<Any>? {
        getRetrofit(baseUrl, timeout ?: 1, header)?.let {
            return when (type) {
                MethodHttp.GET -> {
                    it.create(IService::class.java).get(endpoint).await()
                }
                MethodHttp.POST -> {
                    it.create(IService::class.java).post(endpoint, body).await()
                }
                MethodHttp.PUT -> {
                    it.create(IService::class.java).put(endpoint, body).await()
                }
                MethodHttp.DELETE -> {
                    it.create(IService::class.java).delete(endpoint).await()
                }
                MethodHttp.PATCH -> {
                    it.create(IService::class.java).patch(endpoint, body).await()
                }
                MethodHttp.POST_FORM -> {
                    it.create(IService::class.java)
                        .postEncode(endpoint, getBodyFormUrlEncoded()).await()
                }
                MethodHttp.PUT_FORM -> {
                    it.create(IService::class.java)
                        .putEncode(endpoint, getBodyFormUrlEncoded()).await()
                }
                MethodHttp.PATCH_FORM -> {
                    it.create(IService::class.java)
                        .patchEncode(endpoint, getBodyFormUrlEncoded()).await()
                }
            }
        }
        return null
    }

    private fun getRetrofit(
        baseUrl: String?,
        timeout: Long,
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
        timeout: Long = 1,
        header: Map<String, String>?,
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()


        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        okHttpClientBuilder.connectTimeout(timeout, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(timeout, TimeUnit.MINUTES)

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