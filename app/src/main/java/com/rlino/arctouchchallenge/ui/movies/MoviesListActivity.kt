package com.rlino.arctouchchallenge.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rlino.arctouchchallenge.ui.model.Movie

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.moviesListLiveData.observe(this, Observer {
            it?.let { render(it) }
        })

        viewModel.retrieveMovies()
    }

    private fun render(state: MoviesListUiState) {
        when(state.requestInProgress) {
            true -> showLoading()
            false -> hideLoading()
        }

        when(state.requestSuccess) {
            true -> {
                hideLoading()
                hideError()
            }
        }

        when {
            state.errorMessage.isEmpty() -> hideError()
            state.errorMessage.isNotBlank() -> showError(state.errorMessage)
        }

        when {
            state.movies.isEmpty() -> showEmptyView()
            state.movies.isNotEmpty() -> showMoviesList(state.movies)
        }
    }

    private fun showMoviesList(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showEmptyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun hideError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}