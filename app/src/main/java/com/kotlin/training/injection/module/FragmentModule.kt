package com.kotlin.training.injection.module


import com.kotlin.training.view.ui.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindMovieFragment(): MovieListFragment?
}