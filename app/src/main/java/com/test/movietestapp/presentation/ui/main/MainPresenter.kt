package com.test.movietestapp.presentation.ui.main

import com.test.movietestapp.domain.base.UseCaseObserver
import com.test.movietestapp.domain.usecase.TestUseCase
import com.test.movietestapp.presentation.base.model.BaseModel
import com.test.movietestapp.presentation.base.view.BasePresenter

class MainPresenter(private val testUseCase: TestUseCase) : BasePresenter<MainContract.View>(),
    MainContract.MainPresenter {

    override fun getInfo() {
        testUseCase.execute(InfoSubscriber())
    }

    inner class InfoSubscriber : UseCaseObserver.RxSingle<BaseModel>() {
        override fun onStart() {

        }

        override fun onSuccess(value: BaseModel) {
            view?.showId(1)
        }

        override fun onError(e: Throwable) {

        }
    }

}