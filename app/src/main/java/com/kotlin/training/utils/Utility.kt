package com.kotlin.training.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.android.gms.maps.model.LatLng
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

object Utility {


    private fun getJsonDataFromLocalFile(context: Context, name: String): String? {
        var jsonString: String?
        jsonString = try {
            val inputStream: InputStream = context.getAssets()!!.open(name)
            val fileSize: Int = inputStream.available()
            val buffer = ByteArray(fileSize)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getLatLongFromJsonData(context: Context, name: String): ArrayList<LatLng> {
        var latlngs: ArrayList<LatLng> = ArrayList()
        try {
            val array = JSONArray(getJsonDataFromLocalFile(context, name))
            for (i in 0 until array.length()) {
                val `object` = array.getJSONObject(i)
                val data = `object`.getJSONObject("location")
                val latitude = `data`.getDouble("latitude")
                val longitude = `data`.getDouble("longitude")
                latlngs.add(LatLng(latitude, longitude))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return latlngs
    }
}