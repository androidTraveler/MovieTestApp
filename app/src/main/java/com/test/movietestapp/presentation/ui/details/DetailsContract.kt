package com.test.movietestapp.presentation.ui.details

import com.test.movietestapp.presentation.base.view.BaseView
import com.test.movietestapp.presentation.base.view.Presenter
import com.test.movietestapp.presentation.model.MovieModel

interface DetailsContract {

    interface View : BaseView {
        fun fillData(model: MovieModel?)
    }

    interface DetailsPresenter : Presenter<View> {
        fun checkBundle(model: MovieModel?)
    }
}