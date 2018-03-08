package com.rlino.arctouchchallenge.ui.movies

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rlino.arctouchchallenge.manager.MoviesManager
import com.rlino.arctouchchallenge.manager.status.UpcomingMoviesStatus
import io.reactivex.disposables.CompositeDisposable

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
                .subscribe { upcomingMovieStatus ->
                    moviesListLiveData.value = moviesListLiveData.value?.let { currentState ->
                        when(upcomingMovieStatus) {
                            is UpcomingMoviesStatus.Success ->
                                currentState.copy(
                                        movies = upcomingMovieStatus.movies.map { it.toModel() },
                                        requestSuccess = true,
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
                                        requestInProgress = false,
                                        requestSuccess = false
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