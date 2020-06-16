package com.test.movietestapp.data.network.config

import com.test.movietestapp.data.constants.RestConst
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class DefaultNetworkConfig : NetworkConfiguration {

    companion object {
        private const val HEADER_SESSION_TOKEN = "api_key"
        private const val HEADER_AUTHORIZATION_TOKEN = "2a7a92c10f0c64bab7814fb1e450b6a0"
        private const val CONNECTION_TIMEOUT_SECONDS = 40
    }

    override val baseUrl: String = RestConst.BASE_SERVER_URL

    override val connectionTimeout: Int = CONNECTION_TIMEOUT_SECONDS

    override val interceptors: List<Interceptor> =
        arrayListOf(getAuthRequestInterceptor(), getLoggingInterceptor())

    fun getLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun getAuthRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            val authHeaderKey = this@DefaultNetworkConfig.authTokenHeaderKey
            val authHeaderToken = this@DefaultNetworkConfig.authToken

            if (authHeaderToken != null) {
                val originalHttpUrl = chain.request().url()
                val url =
                    originalHttpUrl.newBuilder().addQueryParameter(authHeaderKey, authHeaderToken)
                        .build()
                request = request.newBuilder()
                    .url(url)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private val authTokenHeaderKey = HEADER_SESSION_TOKEN

    private val authToken: String? = HEADER_AUTHORIZATION_TOKEN
}