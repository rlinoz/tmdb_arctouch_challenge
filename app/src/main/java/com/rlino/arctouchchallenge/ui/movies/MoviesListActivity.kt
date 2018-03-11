package com.rlino.arctouchchallenge.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.rlino.arctouchchallenge.R
import com.rlino.arctouchchallenge.ui.model.Movie
import com.rlino.arctouchchallenge.ui.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main_empty.*
import kotlinx.android.synthetic.main.activity_movies_list.*

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesListActivity : AppCompatActivity(), MoviesListAdapter.OnItemClickListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
    }

    private val moviesListAdapter: MoviesListAdapter by lazy {
        MoviesListAdapter(this)
    }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        initMoviesListView()

        viewModel.moviesListLiveData.observe(this, Observer {
            it?.let { render(it) }
        })

        viewModel.retrieveMovies()
    }

    private fun initMoviesListView() {
        moviesListView.adapter = moviesListAdapter
        moviesListView.layoutManager = layoutManager
        refreshLayout.setOnRefreshListener {
            viewModel.retrieveMovies()
        }

        moviesListView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val itemsCount = layoutManager.itemCount
                val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()
                val loading = viewModel.moviesListLiveData.value?.loadingMore ?: false
                if (!loading && itemsCount == (lastVisibleItemPos + 5)) {
                    viewModel.loadMore()
                }
            }
        })
    }

    private fun render(state: MoviesListUiState) {
        when(state.requestInProgress) {
            true -> showLoading()
            false -> hideLoading()
        }

        when {
            state.errorMessage.isEmpty() -> hideError()
            state.errorMessage.isNotBlank() -> showError(state.errorMessage)
        }

        when {
            state.movies.isEmpty() -> showEmptyView()
            state.movies.isNotEmpty() -> showMoviesList(state.movies)
        }

        when(state.loadingMore) {
            true -> showLoadingMore()
            false -> hideLoadingMore()
        }
    }

    private fun hideLoadingMore() {

    }

    private fun showLoadingMore() {

    }

    private fun showMoviesList(movies: List<Movie>) {
        emptyLayout.visibility = View.GONE
        moviesListView.visibility = View.VISIBLE
        moviesListAdapter.movies = movies
    }

    private fun showEmptyView() {
        emptyLayout.visibility = View.VISIBLE
    }

    private fun showError(errorMessage: String) {

    }

    private fun hideError() {

    }

    private fun hideLoading() {
        refreshLayout.isRefreshing = false
    }

    private fun showLoading() {
        refreshLayout.isRefreshing = true
    }

    override fun onClick(movie: Movie) {
        MovieDetailActivity.start(this, movie)
    }
}