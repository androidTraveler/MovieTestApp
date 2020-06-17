package com.test.movietestapp.presentation.ui.splash

import com.test.movietestapp.presentation.base.view.BaseView
import com.test.movietestapp.presentation.base.view.Presenter

interface SplashContract {

    interface View : BaseView {
        fun startMainActivity()
        fun showNetworkPopupWithRetry()
    }

    interface SplashPresenter : Presenter<View> {
        fun startSplashTask(networkConnected: Boolean)
    }
}