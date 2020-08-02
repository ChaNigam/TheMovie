package com.kotlin.training.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.training.R
import com.kotlin.training.utils.CrashlyticsUtils
import com.kotlin.training.utils.Utility
import kotlinx.android.synthetic.main.fragment_map.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets


class MapCabFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    var latlngs: ArrayList<LatLng> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        latlngs = Utility.getLatLongFromJsonData(this.context!!,"mapData.json")

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map
                , container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {

        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }

    }

    override fun onDestroyView() {

        super.onDestroyView()
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let { googleMap = it }
        var options = MarkerOptions()
        for (point in latlngs) {
            options.position(point)
            options.title("Cab")
            googleMap.addMarker(options)
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(options.position));
        googleMap.maxZoomLevel
    }



}