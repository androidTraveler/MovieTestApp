package com.test.movietestapp.data.repository.remote

import com.test.movietestapp.data.model.response.GenresResponse
import com.test.movietestapp.data.model.transformer.MoviesTransformer
import com.test.movietestapp.data.network.service.ApiService
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val movieTransformer: MoviesTransformer
) : MainRepository {

    override fun getMovies(page: Int?): Single<MovieResponseModel> {
        return apiService.getUpcomingMovies(page)
            .compose(movieTransformer.transformMovieResponseToMovieResponseModel())
    }

    override fun getGenres(): Single<Pair<GenresResponse, GenresResponse>> {
        return Single.zip(
            apiService.getMovieGenres(),
            apiService.getTVGenres(),
            BiFunction { movieGenres, tvGenres -> Pair(movieGenres, tvGenres) }
        )
    }

    override fun searchMovies(page: Int?, query: String): Single<MovieResponseModel> {
        return apiService.searchMovies(page, query)
            .compose(movieTransformer.transformMovieResponseToMovieResponseModel())
    }
}