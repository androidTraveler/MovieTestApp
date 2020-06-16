package com.test.movietestapp.di

import com.test.movietestapp.data.network.config.DefaultNetworkConfig
import com.test.movietestapp.data.network.factory.RxErrorHandlingCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.module.AndroidModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { provideOkHttpClient(get()) }

        provide { provideBaseRetrofit(get(), get()) }

        provide { provideGsonConverterFactory() }

        provide { DefaultNetworkConfig() }
    }

    private fun provideGsonConverterFactory() = GsonConverterFactory.create()

    private fun provideOkHttpClient(defaultNetworkConfig: DefaultNetworkConfig): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(defaultNetworkConfig.getLoggingInterceptor())
            .addInterceptor(defaultNetworkConfig.getAuthRequestInterceptor())
            .build()

    private fun provideBaseRetrofit(
        httpClient: OkHttpClient,
        defaultNetworkConfig: DefaultNetworkConfig
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(defaultNetworkConfig.baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .build()

}