package com.rlino.arctouchchallenge.repository

import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import com.rlino.arctouchchallenge.repository.remote.MovieRemoteDS
import io.reactivex.Observable
import java.io.IOException

/**
 * Created by Lino on 3/7/2018.
 */
class MovieRepository(
        private val movieRemoteDS: MovieRemoteDS = MovieRemoteDS(),
        private var currentUpcomingPage: Int = 0
) {

    fun getUpcomingMovies(): Observable<List<MovieDTO>> {
        return movieRemoteDS.fetchMoviesUpcomingMovies()
                .flatMap {
                    currentUpcomingPage = it.page
                    Observable.just(it.results)
                }
    }

    fun loadMoreMovies(): Observable<List<MovieDTO>> {
        return movieRemoteDS.fetchMoviesUpcomingMovies(++currentUpcomingPage)
                .flatMap {
                    currentUpcomingPage = it.page
                    Observable.just(it.results)
                }
    }

}