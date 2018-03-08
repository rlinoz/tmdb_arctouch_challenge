package com.rlino.arctouchchallenge.manager

import com.rlino.arctouchchallenge.manager.status.UpcomingMoviesStatus
import com.rlino.arctouchchallenge.repository.MovieRepository
import io.reactivex.Observable

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesManager(
        val movieRepository: MovieRepository = MovieRepository()
) {

    fun getUpcomingMovies(): Observable<UpcomingMoviesStatus> {
        return movieRepository.getUpcomingMovies()
                .startWith { UpcomingMoviesStatus.InProgress }
                .map { UpcomingMoviesStatus.Success(it) }
    }

}