package com.jayasuryat.characterdetails.presentation

import android.widget.ImageView
import coil.load

internal object UiUtils {

    fun ImageView.loadImage(url: String) {

        if (url.isEmpty()) return

        this.load(url) {
            crossfade(300)
        }
    }
}