package com.jayasuryat.episodedetails.presentation

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

internal object UiUtils {

    fun ImageView.loadImage(url: String) {

        if (url.isEmpty()) return

        Glide.with(context)
            .load(url)
            .placeholder(ColorDrawable())
            .into(this)
    }
}