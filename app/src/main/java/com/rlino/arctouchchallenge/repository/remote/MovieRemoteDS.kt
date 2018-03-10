package com.rlino.arctouchchallenge.repository.remote

import com.rlino.arctouchchallenge.repository.RetrofitProvider
import com.rlino.arctouchchallenge.repository.dto.response.MovieResponse
import com.rlino.arctouchchallenge.repository.remote.api.MovieApi
import io.reactivex.Observable

/**
 * Created by Lino on 3/7/2018.
 */
class MovieRemoteDS constructor(
        private val api: MovieApi = RetrofitProvider.instance.create(MovieApi::class.java)
) {

    fun fetchMoviesUpcomingMovies(page: Int = 1): Observable<MovieResponse> {
        return api.fetchUpcomingMovies(page)
    }

}