package com.test.movietestapp.data.repository

import com.test.movietestapp.data.model.transformer.MoviesTransformer
import com.test.movietestapp.data.network.service.ApiService
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val movieTransformer: MoviesTransformer
) : MainRepository {

    override fun getMovies(page: Int?): Single<MovieResponseModel> {
        return apiService.getUpcomingMovies(page)
            .compose(movieTransformer.transformMovieResponseToMovieResponseModel())
    }
}