package com.kotlin.training.view.ui

import android.content.Context
import com.kotlin.training.injection.components.AppComponent
import com.kotlin.training.injection.components.DaggerAppComponent
import dagger.android.DaggerApplication



class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AppComponent? {
        return DaggerAppComponent.builder().application(this)?.build()

    }

}
