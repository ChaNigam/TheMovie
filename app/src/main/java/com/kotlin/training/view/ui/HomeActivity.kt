package com.kotlin.training.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.util.Log
import com.kotlin.training.R
import dagger.android.support.DaggerAppCompatActivity



class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("requestCode:",""+requestCode)
//        when (requestCode) {
//            1 -> {
//                if (resultCode == DaggerAppCompatActivity.RESULT_OK && data != null) {
//                    var result = data
//                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                    Log.d("result:",""+result.get(0).toString())
//                    if (result.get(0).toString().equals("Open Camera")) {
//                        var camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                        startActivity(camera)
//                    }
//                }
//
//            }
//        }
//    }
}

