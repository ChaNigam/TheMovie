package com.kotlin.training.injection.module

import android.app.Application
import com.kotlin.training.data.api.MovieApi
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideMovieRepo(api: MovieApi, dao: MovieDAO): MovieRepository {
        return MovieRepository(api, dao)
    }

}