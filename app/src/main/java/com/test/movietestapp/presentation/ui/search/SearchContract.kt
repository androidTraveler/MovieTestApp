package com.test.movietestapp.presentation.ui.search

import com.test.movietestapp.presentation.base.view.BaseView
import com.test.movietestapp.presentation.base.view.Presenter
import com.test.movietestapp.presentation.model.MovieModel

interface SearchContract {

    interface View : BaseView {
        fun showRefresh()
        fun hideRefresh()
        fun fillData(moviesList: MutableList<MovieModel>)
        fun addData(moviesList: MutableList<MovieModel>)
        fun errorGetMovies()
        fun errorNoMoviesByQuery()
        fun showNoMoreMovies()
        fun showEmptyQuery()
        fun changeLastPage(isLastPage: Boolean)
    }

    interface SearchPresenter : Presenter<View> {
        fun searchMovies(networkConnected: Boolean, query: String)
        fun loadMoreData(networkConnected: Boolean)
    }
}