package com.kotlin.training.utils

import android.content.Context
import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsUtils {

    private val crashlytics : FirebaseCrashlytics  = FirebaseCrashlytics.getInstance()

    fun logEvent(context: Context, message:String){
        crashlytics.log(message)
    }
}