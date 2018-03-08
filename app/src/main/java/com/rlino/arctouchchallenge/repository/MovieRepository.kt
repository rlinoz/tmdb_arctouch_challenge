package com.rlino.arctouchchallenge.repository

import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO
import com.rlino.arctouchchallenge.repository.remote.MovieRemoteDS
import com.rlino.arctouchchallenge.ui.model.Movie
import io.reactivex.Observable

/**
 * Created by Lino on 3/7/2018.
 */
class MovieRepository(
        private val movieRemoteDS: MovieRemoteDS = MovieRemoteDS()
) {


    fun getUpcomingMovies(): Observable<List<MovieDTO>> {
        return movieRemoteDS.fetchMoviesUpcomingMovies()
    }

}