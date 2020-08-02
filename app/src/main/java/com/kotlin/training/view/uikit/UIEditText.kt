package com.kotlin.training.view.uikit


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.kotlin.training.R

class UIEditText(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.UIButton,
            0, 0
        ).apply {

            try {
                background = context.getDrawable(R.drawable.rounded_background_grey)
                typeface = Typeface.create("", Typeface.NORMAL)
                textSize = 14.0f
            } finally {
                recycle()
            }
        }
    }
}