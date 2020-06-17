package com.test.movietestapp.domain.usecase

import com.test.movietestapp.data.repository.remote.MainRepository
import com.test.movietestapp.domain.base.UseCase
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single

class SearchMoviesUseCase(private val mainRepository: MainRepository) :
    UseCase.RxSingle<MovieResponseModel>() {

    private var page: Int? = 1
    private var query: String = ""

    fun setPage(page: Int?) {
        this.page = page
    }

    fun setQuery(query: String) {
        this.query = query
    }

    override fun build(): Single<MovieResponseModel> = mainRepository.searchMovies(page, query)

}