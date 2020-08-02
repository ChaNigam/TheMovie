package com.kotlin.training.injection.module

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.kotlin.training.data.room.AppDatabase
import com.kotlin.training.data.room.dao.MovieDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase {
        return Room
                .databaseBuilder(application, AppDatabase::class.java, "Movies.db")
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull database: AppDatabase): MovieDAO {
        return database.movieDao()
    }

}