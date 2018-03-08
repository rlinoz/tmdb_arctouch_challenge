package com.rlino.arctouchchallenge.repository.remote

import com.rlino.arctouchchallenge.repository.RetrofitProvider
import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import com.rlino.arctouchchallenge.repository.remote.api.MovieApi
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by Lino on 3/7/2018.
 */
class MovieRemoteDS constructor(
        private val api: MovieApi = RetrofitProvider.get().create(MovieApi::class.java)
) {

    fun fetchMoviesUpcomingMovies(): Observable<List<MovieDTO>> {
        return api.fetchUpcomingMovies()
    }

}