package com.rlino.arctouchchallenge.manager.status

import com.rlino.arctouchchallenge.ui.model.Movie

/**
 * Created by Lino on 3/7/2018.
 */
sealed class UpcomingMoviesStatus {

    class InProgress(): UpcomingMoviesStatus()
    class Error(val code: Int, val message: String): UpcomingMoviesStatus()
    class Success(val movies: List<Movie>): UpcomingMoviesStatus()

}