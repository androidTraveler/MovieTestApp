package com.test.movietestapp.presentation.ui.main

import com.test.movietestapp.presentation.base.view.BaseView
import com.test.movietestapp.presentation.base.view.Presenter
import com.test.movietestapp.presentation.model.MovieModel

interface MainContract {

    interface View : BaseView {
        fun showRefresh()
        fun hideRefresh()
        fun fillData(moviesList: MutableList<MovieModel>)
        fun addData(moviesList: MutableList<MovieModel>)
        fun errorGetPosts()
        fun showNoMoreMovies()
        fun changeLastPage(isLastPage: Boolean)
    }

    interface MainPresenter : Presenter<View> {
        fun loadMoreData(networkConnected: Boolean)
        fun loadDataFromServer(networkConnected: Boolean)
    }
}