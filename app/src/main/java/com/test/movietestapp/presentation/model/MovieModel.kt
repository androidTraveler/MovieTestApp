package com.test.movietestapp.presentation.model

class MovieModel(
    var popularity: Float?,
    var voteCount: Int?,
    var video: Boolean?,
    var posterPath: String?,
    var id: Int?,
    var adult: Boolean?,
    var backdropPath: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var genres: List<String?>?,
    var title: String?,
    var voteAverage: Float?,
    var overview: String?,
    var releaseDate: String?
)