package com.rlino.arctouchchallenge.ui.movies

import com.rlino.arctouchchallenge.ui.model.Movie

/**
 * Created by Lino on 3/7/2018.
 */
data class MoviesListUiState(
        val movies: List<Movie>,
        val requestInProgress: Boolean,
        val errorMessage: String,
        val loadingMore: Boolean
) {
    companion object {
        fun init(): MoviesListUiState {
            return MoviesListUiState(emptyList(), false, "", false)
        }
    }

}

//object Loading : MoviesListUiState()
//object Empty : MoviesListUiState()
//object Idle : MoviesListUiState()
//class Error(val errorMessage: String) : MoviesListUiState()
//class ListMovies(val movies: List<Movie>) : MoviesListUiState()