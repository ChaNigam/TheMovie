package com.kotlin.training.data.api

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.kotlin.training.R
import com.kotlin.training.data.room.dao.MovieDAO
import com.kotlin.training.utils.Constants
import com.kotlin.training.utils.NoInternetException
import com.kotlin.training.view.ui.BaseApplication
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor
@Inject constructor(
var application: Context)
: Interceptor {
    private val appContext = application.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .addQueryParameter("language", Constants.language)
                .build()

        val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
        if(!isNetworkAvailable())
            throw NoInternetException(appContext.getString(R.string.network_error))
        return chain.proceed(request)
    }

    fun isNetworkAvailable(): Boolean {
        if (appContext == null) return false
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager?.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}