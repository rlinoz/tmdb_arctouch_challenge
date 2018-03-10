package com.rlino.arctouchchallenge.ui.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rlino.arctouchchallenge.manager.MoviesManager
import com.rlino.arctouchchallenge.manager.status.UpcomingMoviesStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Lino on 3/7/2018.
 */
class MoviesListViewModel : ViewModel() {

    val moviesListLiveData = MutableLiveData<MoviesListUiState>()

    private val moviesManager = MoviesManager()
    private val compositeDisposable = CompositeDisposable()


    fun retrieveMovies() {
        if(moviesListLiveData.value == null)
            moviesListLiveData.value = MoviesListUiState.init()

        compositeDisposable.add(moviesManager.getUpcomingMovies()
                .startWith(UpcomingMoviesStatus.InProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { upcomingMovieStatus ->
                    moviesListLiveData.value = moviesListLiveData.value?.let { currentState ->
                        when(upcomingMovieStatus) {
                            is UpcomingMoviesStatus.Success ->
                                currentState.copy(
                                        movies = upcomingMovieStatus.movies.map { return@map it.toModel() },
                                        requestInProgress = false,
                                        errorMessage = ""
                                )
                            is UpcomingMoviesStatus.InProgress ->
                                currentState.copy(
                                        requestInProgress = true,
                                        errorMessage = ""
                                )
                            is UpcomingMoviesStatus.Error ->
                                currentState.copy(
                                        errorMessage = upcomingMovieStatus.message,
                                        requestInProgress = false
                                )
                        }
                    }
                })
    }

    fun loadMore() {
        compositeDisposable.add(moviesManager.loadMoreMovies()
                .startWith(UpcomingMoviesStatus.InProgress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { upcomingMovieStatus ->
                    moviesListLiveData.value = moviesListLiveData.value?.let { currentState ->
                        when(upcomingMovieStatus) {
                            is UpcomingMoviesStatus.Success ->
                                currentState.copy(
                                        movies = currentState.movies.union(upcomingMovieStatus.movies.map { return@map it.toModel() }).toList(),
                                        loadingMore = false,
                                        errorMessage = ""
                                )
                            is UpcomingMoviesStatus.InProgress ->
                                currentState.copy(
                                        errorMessage = "",
                                        loadingMore = true
                                )
                            is UpcomingMoviesStatus.Error ->
                                currentState.copy(
                                        errorMessage = upcomingMovieStatus.message,
                                        loadingMore = false
                                )
                        }
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        if(!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}