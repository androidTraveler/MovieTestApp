package com.test.movietestapp.data.model.transformer

import com.test.movietestapp.data.model.response.MoviesResponse
import com.test.movietestapp.presentation.model.MovieModel
import com.test.movietestapp.presentation.model.MovieResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.functions.Function3

class MoviesTransformer {

    fun transformMovieResponseToMovieResponseModel(): SingleTransformer<MoviesResponse, MovieResponseModel> {
        return SingleTransformer { upstream ->
            upstream
                .flatMap { moviesResponse ->
                    Single.zip(
                        Single.just(moviesResponse.page),
                        Single.just(moviesResponse.totalPages),
                        Single.just(moviesResponse.movies)
                            .flatMap { data ->
                                Observable.fromIterable(data)
                                    .map { item -> mapMovieToMovieModel(item) }.toList()
                            },
                        Function3 { page: Int?, totalPages: Int?, movieList: List<MovieModel> ->
                            MovieResponseModel(page, totalPages, movieList)
                        }
                    )
                }
        }
    }

    private fun mapMovieToMovieModel(item: MoviesResponse.Movie?): MovieModel {
        return MovieModel(
            item?.popularity,
            item?.voteCount,
            item?.video,
            item?.posterPath,
            item?.id,
            item?.adult,
            item?.backdropPath,
            item?.originalLanguage,
            item?.originalTitle,
            null, //TODO solve genre
            item?.title,
            item?.voteAverage,
            item?.overview,
            item?.releaseDate
        )
    }
}