package com.test.movietestapp.data.repository.remote

import com.test.movietestapp.data.model.response.GenresResponse
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single

interface MainRepository {

    fun getMovies(page: Int?): Single<MovieResponseModel>
    fun getGenres(): Single<Pair<GenresResponse, GenresResponse>>
    fun searchMovies(page: Int?, query: String): Single<MovieResponseModel>
}