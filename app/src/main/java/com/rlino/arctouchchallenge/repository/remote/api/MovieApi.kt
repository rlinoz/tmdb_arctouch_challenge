package com.rlino.arctouchchallenge.repository.remote.api

import com.rlino.arctouchchallenge.repository.dto.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Lino on 3/7/2018.
 */
interface MovieApi {

    @GET("movie/upcoming")
    fun fetchUpcomingMovies(
            @Query("page") page: Int
    ): Observable<MovieResponse>

}