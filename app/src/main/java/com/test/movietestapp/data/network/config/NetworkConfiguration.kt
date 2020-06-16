package com.test.movietestapp.data.network.config

import okhttp3.Interceptor

interface NetworkConfiguration {

    val connectionTimeout: Int

    val baseUrl: String

    val interceptors: List<Interceptor>

}