package com.test.movietestapp.presentation.ui.splash

import com.test.movietestapp.presentation.base.view.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashPresenter : BasePresenter<SplashContract.View>(),
    SplashContract.SplashPresenter {

    override fun startSplashTask() { // imitation of some tasks that run on splash screen (check token etc.)
        Observable.timer(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { view?.startMainActivity() }
            .subscribe()
    }


}