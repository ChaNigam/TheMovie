package com.kotlin.training.injection.components

import android.app.Application
import com.kotlin.training.injection.module.*
import com.kotlin.training.view.ui.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, FragmentModule::class,
    ViewModelFactoryModule::class, NetworkModule::class, RoomModule::class])
interface AppComponent : AndroidInjector<BaseApplication?> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder?

        fun build(): AppComponent?


    }
}
