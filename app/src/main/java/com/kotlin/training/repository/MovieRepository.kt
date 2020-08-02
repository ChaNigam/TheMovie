package com.kotlin.training.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlin.training.data.api.ApiResponse
import com.kotlin.training.data.api.MovieApi
import com.kotlin.training.data.model.PopularMovies
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.utils.ApiException
import com.kotlin.training.utils.Constants
import com.kotlin.training.utils.NoInternetException
import com.kotlin.training.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
        var api: MovieApi,
        var dao: MovieDAO
) {
    private var statusLiveData: MutableLiveData<ErrorState> = MutableLiveData()
    private lateinit var movies: MutableLiveData<List<Movie>>
    private val compositeDisposable = CompositeDisposable()

    fun getAllMovies(): MutableLiveData<List<Movie>> {
        movies = loadFromApi()
        return movies
    }

    fun getState(): MutableLiveData<ErrorState> {
        return statusLiveData
    }

    private fun loadFromApi(): MutableLiveData<List<Movie>> {
//        val movies: MutableLiveData<List<Movie>> = MutableLiveData()
        movies = MutableLiveData()

        compositeDisposable.add(

                api.getPopularMovies(Constants.page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            statusLiveData.value = ErrorState.LOADING
                        }
                        .doOnTerminate {
                        }
                        .subscribe(
                                // Add result
                                { result ->
                                    var res = ApiResponse(result)
                                    if (res.isSuccessful) {
                                        Log.d("res", "" + res.body)
                                        var list: PopularMovies? = res.body
                                        insert(list!!.results)
                                    } else {
                                        Log.d("result erer", res.message)
                                        statusLiveData.value = ErrorState.REQUEST
                                    }
                                },
                                { error ->
                                    when (error) {
                                        is NoInternetException -> loadFromDb()
//                                            statusLiveData.value = ErrorState.ERROR
                                        is ApiException -> statusLiveData.value = ErrorState.REQUEST
                                    }

                                }
                        )
        )
        return movies
    }

    private fun insert(list: List<Movie>?) {
        compositeDisposable.add(
                list?.let {
                    dao.insertMovieList(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe {
                                statusLiveData.value = ErrorState.LOADING
                            }
                            .doOnTerminate {
                                statusLiveData.value = ErrorState.LOADED
                            }
                            .subscribe(
                                    {
                                        Log.d("inserted", "in db" + list.size)
                                        statusLiveData.value = ErrorState.LOADED
                                        movies.value = list
                                        clearDisposable()
                                    },
                                    { error ->
                                        Log.d("err insert", error.message)
                                        statusLiveData.value = ErrorState.ERROR
                                    }
                            )
                }!!
        )
//        movies.value = list
//        return movies
    }

    private fun loadFromDb(): MutableLiveData<List<Movie>> {
//        val movies: MutableLiveData<List<Movie>> = MutableLiveData()

        compositeDisposable.add(
                dao.getMovieList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            statusLiveData.value = ErrorState.LOADING
                        }
                        .doOnTerminate {
                            statusLiveData.value = ErrorState.LOADED
                        }
                        .subscribe(
                                { result ->

                                    Log.d("result. load", "" + result.size)
                                    movies.value = result
                                    if (result.isEmpty()) statusLiveData.value = ErrorState.EMPTY else statusLiveData.value = ErrorState.LOADED
                                    clearDisposable()
                                },
                                { error ->
                                    Log.d("err load", error.message)
                                    statusLiveData.value = ErrorState.ERROR
                                }
                        )
        )
        return movies
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}