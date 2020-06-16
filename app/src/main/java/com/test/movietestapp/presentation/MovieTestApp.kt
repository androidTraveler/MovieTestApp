package com.test.movietestapp.presentation

import android.app.Application
import com.test.movietestapp.di.*
import org.koin.android.ext.android.startAndroidContext

class MovieTestApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startAndroidContext(
            this, listOf(
                AppModule(),
                ActivityModule(),
                UseCaseModule(),
                NetworkModule(),
                RepositoryModule()
            )
        )
    }
}