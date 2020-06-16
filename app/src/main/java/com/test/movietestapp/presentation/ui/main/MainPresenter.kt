package com.test.movietestapp.presentation.ui.main

import com.test.movietestapp.domain.usecase.GetMoviesUseCase
import com.test.movietestapp.presentation.base.view.BasePresenter
import com.test.movietestapp.presentation.model.MovieModel

class MainPresenter(private val getMoviesUseCase: GetMoviesUseCase) :
    BasePresenter<MainContract.View>(),
    MainContract.MainPresenter {

    private var moviesList: MutableList<MovieModel>? = null
    private var page: Int? = null
    private var totalPages: Int? = null

    override fun loadDataFromServer(networkConnected: Boolean) {
        if (networkConnected) {
            view?.showRefresh()
            getMoviesUseCase.execute(
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
                    view?.errorGetPosts()
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
            getMoviesUseCase.setPage(page?.inc())
            getMoviesUseCase.execute(
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
                    view?.errorGetPosts()
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
            view?.errorGetPosts()
        }
    }

    private fun addData() {
        view?.hideRefresh()
        if (!moviesList.isNullOrEmpty()) {
            view?.changeLastPage(totalPages == page)
            view?.addData(moviesList!!)
        } else {
            view?.errorGetPosts()
        }
    }

}