package com.test.movietestapp.presentation.ui.details

import com.test.movietestapp.presentation.base.view.BasePresenter
import com.test.movietestapp.presentation.model.MovieModel

class DetailsPresenter : BasePresenter<DetailsContract.View>(),
    DetailsContract.DetailsPresenter {
    override fun checkBundle(model: MovieModel?) {
        view?.fillData(model)
    }

}