package com.kotlin.training.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.kotlin.training.repository.MovieRepository
import com.kotlin.training.repository.ErrorState

import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.view.ui.MovieListAdapter
import javax.inject.Inject


class HomeViewModel
@Inject constructor(
        val repository: MovieRepository
) : ViewModel() {


    val movieListAdapter: MovieListAdapter = MovieListAdapter()

    fun getMovies(): MutableLiveData<List<Movie>> {
        return repository.getAllMovies()
    }

    fun getState(): MutableLiveData<ErrorState> {
        return repository.getState()
    }

}