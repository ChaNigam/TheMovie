package com.kotlin.training.injection.module

import com.kotlin.training.view.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeHomeActivity(): HomeActivity?
}