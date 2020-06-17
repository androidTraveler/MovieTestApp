package com.test.movietestapp.di

import com.test.movietestapp.presentation.ui.details.DetailsContract
import com.test.movietestapp.presentation.ui.details.DetailsPresenter
import com.test.movietestapp.presentation.ui.main.MainContract
import com.test.movietestapp.presentation.ui.main.MainPresenter
import com.test.movietestapp.presentation.ui.search.SearchContract
import com.test.movietestapp.presentation.ui.search.SearchPresenter
import com.test.movietestapp.presentation.ui.splash.SplashContract
import com.test.movietestapp.presentation.ui.splash.SplashPresenter
import org.koin.android.module.AndroidModule

class ActivityModule : AndroidModule() {

    companion object {
        const val CTX_SPLASH_ACTIVITY = "SplashActivity"
        const val CTX_MAIN_ACTIVITY = "MainActivity"
        const val CTX_DETAILS_ACTIVITY = "DetailsActivity"
        const val CTX_SEARCH_ACTIVITY = "SearchActivity"
    }

    override fun context() = applicationContext {
        context(name = CTX_SPLASH_ACTIVITY) {
            provide { SplashPresenter(get(), get()) } bind (SplashContract.SplashPresenter::class)
        }
        context(name = CTX_MAIN_ACTIVITY) {
            provide { MainPresenter(get()) } bind (MainContract.MainPresenter::class)
        }
        context(name = CTX_DETAILS_ACTIVITY) {
            provide { DetailsPresenter() } bind (DetailsContract.DetailsPresenter::class)
        }
        context(name = CTX_DETAILS_ACTIVITY) {
            provide { SearchPresenter(get()) } bind (SearchContract.SearchPresenter::class)
        }

    }
}