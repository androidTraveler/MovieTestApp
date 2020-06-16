package com.test.movietestapp.presentation.ui.main

import com.test.movietestapp.presentation.base.view.BaseView
import com.test.movietestapp.presentation.base.view.Presenter

interface MainContract {

    interface View : BaseView {
        fun showId(mId: Long)

    }

    interface MainPresenter : Presenter<View> {
        fun getInfo()
    }
}