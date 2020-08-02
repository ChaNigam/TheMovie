package com.kotlin.training.data.api


import com.kotlin.training.data.model.PopularMovies
import io.reactivex.Observable


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

//    @GET("movie/popular")
//    fun getPopularMovies(
//        @Query("page") pageIndex: Int
//    ): Observable<PopularMovies>

    @GET("movie/popular")
    fun getPopularMovies(
            @Query("page") pageIndex: Int
    ): Observable<Response<PopularMovies>>
}