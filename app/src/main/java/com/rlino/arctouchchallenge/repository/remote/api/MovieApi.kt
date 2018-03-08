package com.rlino.arctouchchallenge.repository.remote.api

import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Lino on 3/7/2018.
 */
interface MovieApi {

    @GET("")
    fun fetchUpcomingMovies(): Observable<List<MovieDTO>>

}