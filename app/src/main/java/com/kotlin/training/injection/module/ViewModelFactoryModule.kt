package com.kotlin.training.injection.module

import androidx.lifecycle.ViewModelProvider
import com.kotlin.training.injection.components.ViewModelFactory

import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?


}