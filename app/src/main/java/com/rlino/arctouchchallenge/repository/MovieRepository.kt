package com.rlino.arctouchchallenge.repository

import com.rlino.arctouchchallenge.repository.dto.response.GenreDTO
import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import com.rlino.arctouchchallenge.repository.dto.response.MovieResponse
import com.rlino.arctouchchallenge.repository.remote.MovieRemoteDS
import io.reactivex.Observable

/**
 * Created by Lino on 3/7/2018.
 */
class MovieRepository(
        private val movieRemoteDS: MovieRemoteDS = MovieRemoteDS(),
        private var currentUpcomingPage: Int = 0
) {

    //This should eventually be moved to a database, maybe
    var genres = emptyList<GenreDTO>()

    fun getGenres(): Observable<List<GenreDTO>> {
        if(genres.isEmpty())
            return movieRemoteDS.fetchGenres()
                    .map { it.genres }
                    .flatMap {
                        genres = it
                        Observable.just(genres)
                    }
        else
            return Observable.just(genres)
    }

    fun getUpcomingMovies(): Observable<List<MovieDTO>> {
        return mapToMovies(movieRemoteDS.fetchMoviesUpcomingMovies(1))
    }

    fun loadMoreMovies(): Observable<List<MovieDTO>> {
        return mapToMovies(movieRemoteDS.fetchMoviesUpcomingMovies(++currentUpcomingPage))
                .doOnError {
                    if(currentUpcomingPage > 0)
                        currentUpcomingPage--
                }

    }

    private fun mapToMovies(fetchMoviesUpcomingMovies: Observable<MovieResponse>): Observable<List<MovieDTO>> {
        return fetchMoviesUpcomingMovies.flatMap {
            currentUpcomingPage = it.page
            if(it.totalPages < currentUpcomingPage)
                currentUpcomingPage = it.totalPages

            Observable.just(it.results)
        }
    }

}