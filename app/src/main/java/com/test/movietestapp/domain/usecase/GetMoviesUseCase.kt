package com.test.movietestapp.domain.usecase

import com.test.movietestapp.data.repository.MainRepository
import com.test.movietestapp.domain.base.UseCase
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single

class GetMoviesUseCase(private val mainRepository: MainRepository) :
    UseCase.RxSingle<MovieResponseModel>() {

    private var page: Int? = 1

    fun setPage(page: Int?) {
        this.page = page
    }

    override fun build(): Single<MovieResponseModel> = mainRepository.getMovies(page)

}