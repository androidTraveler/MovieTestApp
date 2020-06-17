package com.test.movietestapp.presentation.ui.search

import com.test.movietestapp.domain.usecase.SearchMoviesUseCase
import com.test.movietestapp.presentation.base.view.BasePresenter
import com.test.movietestapp.presentation.model.MovieModel

class SearchPresenter(private val searchMoviesUseCase: SearchMoviesUseCase) :
    BasePresenter<SearchContract.View>(),
    SearchContract.SearchPresenter {

    private var moviesList: MutableList<MovieModel>? = null
    private var page: Int? = null
    private var totalPages: Int? = null
    private var query: String = ""

    override fun searchMovies(networkConnected: Boolean, query: String) {
        if (query.trim().isEmpty()) {
            view?.showEmptyQuery()
            view?.hideRefresh()
            return
        }
        if (networkConnected) {
            view?.showRefresh()
            this.query = query
            searchMoviesUseCase.setQuery(query)
            searchMoviesUseCase.execute(
                onSuccess = { response ->
                    run {
                        page = response.page
                        totalPages = response.totalPages
                        moviesList = response.movieModels.toMutableList()
                        proceedData()
                    }
                },
                onError = {
                    it.printStackTrace()
                    view?.errorGetMovies()
                    view?.hideRefresh()
                })
        } else {
            view?.showNetworkPopup()
            view?.hideRefresh()
        }
    }

    override fun loadMoreData(networkConnected: Boolean) {
        if (totalPages == page) {
            view?.showNoMoreMovies()
            return
        }
        if (networkConnected) {
            view?.showRefresh()
            searchMoviesUseCase.setPage(page?.inc())
            searchMoviesUseCase.setQuery(query)
            searchMoviesUseCase.execute(
                onSuccess = { response ->
                    run {
                        page = response.page
                        totalPages = response.totalPages
                        moviesList = response.movieModels.toMutableList()
                        addData()
                    }
                },
                onError = {
                    it.printStackTrace()
                    view?.errorGetMovies()
                    view?.hideRefresh()
                })
        } else {
            view?.showNetworkPopup()
            view?.hideRefresh()
        }
    }

    private fun proceedData() {
        view?.hideRefresh()
        if (!moviesList.isNullOrEmpty()) {
            view?.changeLastPage(totalPages == page)
            view?.fillData(moviesList!!)
        } else {
            view?.fillData(mutableListOf())
            view?.errorNoMoviesByQuery()
        }
    }

    private fun addData() {
        view?.hideRefresh()
        if (!moviesList.isNullOrEmpty()) {
            view?.changeLastPage(totalPages == page)
            view?.addData(moviesList!!)
        } else {
            view?.errorGetMovies()
        }
    }
}