package com.test.movietestapp.data.model.response

import com.google.gson.annotations.SerializedName

class MoviesResponse {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null

    @SerializedName("results")
    var movies: List<Movie?>? = null

    class Movie {
        @SerializedName("popularity")
        var popularity: Float? = null

        @SerializedName("vote_count")
        var voteCount: Int? = null

        @SerializedName("video")
        var video: Boolean? = null

        @SerializedName("poster_path")
        var posterPath: String? = null

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("adult")
        var adult: Boolean? = null

        @SerializedName("backdrop_path")
        var backdropPath: String? = null

        @SerializedName("original_language")
        var originalLanguage: String? = null

        @SerializedName("original_title")
        var originalTitle: String? = null

        @SerializedName("")
        var genreIds: List<Pair<Int?, Int?>?>? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("vote_average")
        var voteAverage: Float? = null

        @SerializedName("overview")
        var overview: String? = null

        @SerializedName("release_date")
        var releaseDate: String? = null
    }
}