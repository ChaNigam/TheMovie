package com.kotlin.training.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.training.data.room.entity.Movie


class MovieViewModel: ViewModel() {
    private val voteAverage = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val overview = MutableLiveData<String>()
    private val posterPath = MutableLiveData<String>()

    fun bind(movie: Movie){
        voteAverage.value = movie.vote_average.toString()
        title.value = movie.title
        overview.value = movie.overview
        posterPath.value = movie.poster_path
    }

    fun getMovieTitle():MutableLiveData<String>{
        return title
    }
    fun getMovieOverview():MutableLiveData<String>{
        return overview
    }
    fun getVoting():MutableLiveData<String>{
        return voteAverage
    }
    fun getPosterPath():MutableLiveData<String>{
        return posterPath
    }
}
