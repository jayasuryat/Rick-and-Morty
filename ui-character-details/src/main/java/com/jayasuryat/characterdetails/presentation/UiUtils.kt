package com.jayasuryat.characterdetails.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

internal object UiUtils {

    fun ImageView.loadImage(url: String) {

        if (url.isEmpty()) return

        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(300))
            .into(this)
    }
}