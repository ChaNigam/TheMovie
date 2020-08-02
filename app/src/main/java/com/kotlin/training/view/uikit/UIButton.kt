package com.kotlin.training.view.uikit


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button
import com.kotlin.training.R


class UIButton(context: Context, attrs: AttributeSet) : Button(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.UIButton,
            0, 0
        ).apply {

            try {
                background = context.getDrawable(R.drawable.ui_button_background)
                typeface = Typeface.create("", Typeface.BOLD)
                textSize = 14.0f

            } finally {
                recycle()
            }
        }
    }
}