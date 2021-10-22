package com.jayasuryat.characterdetails.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide

internal object UiUtils {

    fun ImageView.loadImage(url: String) {

        if (url.isEmpty()) return

        Glide.with(context)
            .load(url)
            .into(this)
    }
}