package com.rlino.arctouchchallenge.manager

import com.rlino.arctouchchallenge.manager.status.UpcomingMoviesStatus
import com.rlino.arctouchchallenge.repository.MovieRepository
import com.rlino.arctouchchallenge.repository.dto.response.GenreDTO
import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import com.rlino.arctouchchallenge.ui.model.Movie
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.net.UnknownHostException

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesManager(
        private val movieRepository: MovieRepository = MovieRepository()
) {

    fun getUpcomingMovies(): Observable<UpcomingMoviesStatus> {
        return threatErrors(
                mapSuccess(
                        zipWithGenre(movieRepository.getUpcomingMovies())
                )
        )
    }

    fun loadMoreMovies(): Observable<UpcomingMoviesStatus> {
        return threatErrors(
                mapSuccess(
                        zipWithGenre(movieRepository.loadMoreMovies())
                )
        )
    }

    private fun zipWithGenre(observable: Observable<List<MovieDTO>>): Observable<List<Movie>> {
        return observable.zipWith(movieRepository.getGenres(), BiFunction({movies, genres ->
            movies.map {
                val movieGenres = it.findGenres(genres)
                it.toModel(movieGenres)
            }
        }))
    }

    private fun mapSuccess(observable: Observable<List<Movie>>): Observable<UpcomingMoviesStatus> {
        return observable.map { UpcomingMoviesStatus.Success(it) as UpcomingMoviesStatus }
    }

    private fun threatErrors(observable: Observable<UpcomingMoviesStatus>): Observable<UpcomingMoviesStatus> {
        return observable.onErrorReturn {
            if(it is UnknownHostException)
                return@onErrorReturn UpcomingMoviesStatus.Error(-1, "Check your internet connection")
            return@onErrorReturn UpcomingMoviesStatus.Error(500, "Ops, looks like something went wrong")
        }
    }

}