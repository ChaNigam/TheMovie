package com.kotlin.training.injection.module

import androidx.lifecycle.ViewModel
import com.kotlin.training.injection.components.ViewModelKey
import com.kotlin.training.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel?): ViewModel?
}