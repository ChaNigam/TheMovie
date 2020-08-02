package com.kotlin.training.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.data.room.entity.Movie


@Database(entities = [(Movie::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDAO

}
