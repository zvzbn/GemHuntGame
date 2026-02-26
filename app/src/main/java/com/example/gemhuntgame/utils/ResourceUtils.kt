package com.example.gemhuntgame.utils

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat

object ResourceUtils {
    fun loadImage(context: Context, resId: Int, imageView: ImageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(context, resId))
    }
}