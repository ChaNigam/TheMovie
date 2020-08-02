package com.kotlin.training.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.kotlin.training.R
import com.kotlin.training.view.ui.HomeActivity


object Navigation {

    fun navigate(context: Context, className: Int) {
        var intent: Intent
        when (className) {
            R.string.Home -> {
                intent = Intent(context, HomeActivity::class.java)
                (context as Activity).finish()

            }

            1 -> intent = Intent(context, HomeActivity::class.java)

            else -> intent = Intent(context, HomeActivity::class.java)
        }

        context.startActivity(intent)

    }

}