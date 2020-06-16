package com.test.movietestapp.di

import com.test.movietestapp.presentation.ui.main.MainContract
import com.test.movietestapp.presentation.ui.main.MainPresenter
import com.test.movietestapp.presentation.ui.splash.SplashContract
import com.test.movietestapp.presentation.ui.splash.SplashPresenter
import org.koin.android.module.AndroidModule

class ActivityModule : AndroidModule() {

    companion object {
        const val CTX_SPLASH_ACTIVITY = "SplashActivity"
        const val CTX_MAIN_ACTIVITY = "MainActivity"
    }

    override fun context() = applicationContext {
        context(name = CTX_SPLASH_ACTIVITY) {
            provide { SplashPresenter() } bind (SplashContract.SplashPresenter::class)
        }
        context(name = CTX_MAIN_ACTIVITY) {
            provide { MainPresenter(get()) } bind (MainContract.MainPresenter::class)
        }

    }
}