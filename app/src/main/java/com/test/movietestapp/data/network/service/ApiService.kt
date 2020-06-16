package com.test.movietestapp.data.network.service

import com.test.movietestapp.data.constants.RestConst
import com.test.movietestapp.data.model.response.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(RestConst.UPCOMING_MOVIES_URL)
    fun getUpcomingMovies(@Query("page") page: Int?): Single<MoviesResponse>
}