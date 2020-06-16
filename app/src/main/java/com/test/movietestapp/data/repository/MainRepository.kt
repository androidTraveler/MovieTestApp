package com.test.movietestapp.data.repository

import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single

interface MainRepository {

    fun getMovies(page: Int?): Single<MovieResponseModel>
}