package com.test.movietestapp.domain.usecase

import com.test.movietestapp.data.model.response.GenresResponse
import com.test.movietestapp.data.repository.remote.MainRepository
import com.test.movietestapp.domain.base.UseCase
import io.reactivex.Single

class GetGenresUseCase(private val mainRepository: MainRepository) :
    UseCase.RxSingle<Pair<GenresResponse, GenresResponse>>() {

    override fun build(): Single<Pair<GenresResponse, GenresResponse>> = mainRepository.getGenres()
}