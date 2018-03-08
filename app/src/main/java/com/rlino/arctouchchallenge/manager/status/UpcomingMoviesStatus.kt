package com.rlino.arctouchchallenge.manager.status

import com.rlino.arctouchchallenge.repository.dto.response.MovieDTO

/**
 * Created by Lino on 3/7/2018.
 */
sealed class UpcomingMoviesStatus {

    object InProgress: UpcomingMoviesStatus()
    class Error(val code: Int, val message: String): UpcomingMoviesStatus()
    class Success(val movies: List<MovieDTO>): UpcomingMoviesStatus()

}