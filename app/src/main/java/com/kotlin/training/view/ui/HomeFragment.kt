package com.kotlin.training.view.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kotlin.training.R
import com.kotlin.training.databinding.FragmentHomeBinding
import com.kotlin.training.utils.CrashlyticsUtils
import dagger.android.support.DaggerAppCompatActivity
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private var fragmentHomeBinding: FragmentHomeBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            navController = Navigation.findNavController(view)
            fragmentHomeBinding = FragmentHomeBinding.bind(view)
            fragmentHomeBinding?.btnFetch?.setOnClickListener(this)
            fragmentHomeBinding?.imvSpeak?.setOnClickListener(this)
        } catch (exc: Exception) {
            this.context?.let { CrashlyticsUtils.logEvent(it, exc.toString()) }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            fragmentHomeBinding?.btnFetch?.id -> navController?.navigate(R.id.action_homeFragment_to_movieListFragment)
            fragmentHomeBinding?.imvSpeak?.id -> speak()
        }
    }

    override fun onDestroyView() {
        fragmentHomeBinding?.unbind()
        fragmentHomeBinding = null
        navController = null
        super.onDestroyView()
    }

    private fun speak() {
        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
        try {
            startActivityForResult(intent, 1);
        } catch (exc: ActivityNotFoundException) {
//            Toast.makeText(getApplicationContext(),
//                    "Sorry your device not supported",
//                    Toast.LENGTH_SHORT).show();
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("requestCode:",""+requestCode)
        when (requestCode) {
            1 -> {
                if (resultCode == DaggerAppCompatActivity.RESULT_OK && data != null) {
                    var result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.d("result:",""+result.get(0).toString())
                    if (result.get(0).toString().equals("open map")) {
                        navController?.navigate(R.id.action_homeFragment_to_mapFragment)
                    }
                    else if (result.get(0).toString().equals("open camera")) {
                        openCamera()
                    }
                }

            }
        }
    }



    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }
    private fun openMap(){
//        val strUri = "http://maps.google.com/maps?q=loc:" + "12.950415" + "," + "77.642605" + " (" + "BMW" + ")"
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
//
//        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")


        val gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=18.519513,73.868315&destination=18.518496,73.879259&waypoints=18.520561,73.872435|18.519254,73.876614|18.52152,73.877327|18.52019,73.879935&travelmode=driving")
        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            try {
                val unrestrictedIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(unrestrictedIntent)
            } catch (innerEx: ActivityNotFoundException) {
                Toast.makeText(this.context, "Please install a maps application", Toast.LENGTH_LONG).show()
            }
        }
        startActivity(intent)

    }

}
