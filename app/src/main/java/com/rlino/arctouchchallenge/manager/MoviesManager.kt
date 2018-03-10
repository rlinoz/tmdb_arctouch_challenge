package com.rlino.arctouchchallenge.manager

import com.rlino.arctouchchallenge.manager.status.UpcomingMoviesStatus
import com.rlino.arctouchchallenge.repository.MovieRepository
import io.reactivex.Observable
import java.net.UnknownHostException

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesManager(
        private val movieRepository: MovieRepository = MovieRepository()
) {

    fun getUpcomingMovies(): Observable<UpcomingMoviesStatus> {
        return movieRepository.getUpcomingMovies()
                .map { UpcomingMoviesStatus.Success(it) as UpcomingMoviesStatus }
                .onErrorReturn {
                    if(it is UnknownHostException)
                        return@onErrorReturn UpcomingMoviesStatus.Error(-1, "Check your internet connection")
                    return@onErrorReturn UpcomingMoviesStatus.Error(500, "Ops, looks like something went wrong")
                }
    }

    fun loadMoreMovies(): Observable<UpcomingMoviesStatus> {
        return movieRepository.loadMoreMovies()
                .map { UpcomingMoviesStatus.Success(it) as UpcomingMoviesStatus }
                .onErrorReturn {
                    if(it is UnknownHostException)
                        return@onErrorReturn UpcomingMoviesStatus.Error(-1, "Check your internet connection")
                    return@onErrorReturn UpcomingMoviesStatus.Error(500, "Ops, looks like something went wrong")
                }
    }

}