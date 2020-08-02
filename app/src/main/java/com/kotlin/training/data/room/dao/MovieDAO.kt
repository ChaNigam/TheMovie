package com.kotlin.training.data.room.dao

import androidx.room.*
import com.kotlin.training.data.room.entity.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMovieList(movies: List<Movie>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: List<Movie>): Completable

    @Query("DELETE FROM Movie")
    fun deleteAll(): Single<Int>


    @Query("SELECT * FROM Movie")
    fun getMovieList(): Observable<List<Movie>>

}