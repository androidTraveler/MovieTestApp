package com.test.movietestapp.data.network.config

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class DefaultNetworkConfig : NetworkConfiguration {

    companion object {
        private val HEADER_SESSION_TOKEN = "Token"
        private val CONNECTION_TIMEOUT_SECONDS = 40
    }

    override val baseUrl: String = ""

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
                request = request.newBuilder()
                    .addHeader(authHeaderKey, authHeaderToken)
                    .build()
            }
            chain.proceed(request)
        }
    }

    private val authTokenHeaderKey = HEADER_SESSION_TOKEN

    private val authToken: String? = null
}