package com.kotlin.training.utils

import android.app.Activity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase


object FirebaseUtils {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    fun setUserProperty(property: String, value: String) {
        firebaseAnalytics.setUserProperty(property, value)
    }

    fun setCurrentScreen(context: Activity, screen: String) {
        firebaseAnalytics.setCurrentScreen(context, screen, null /* class override */)
    }

    fun logEvent(name:String, param: String){
        var bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.METHOD, param)
        firebaseAnalytics.logEvent(name, bundle)
    }
}


