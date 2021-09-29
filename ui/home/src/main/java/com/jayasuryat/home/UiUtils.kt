package com.jayasuryat.home

import android.annotation.SuppressLint
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
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

    @SuppressLint("ClickableViewAccessibility")
    fun View.setOnClickListenerWithPoint(action: (Point) -> Unit) {
        val coordinates = Point()
        val screenPosition = IntArray(2)
        setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.getLocationOnScreen(screenPosition)
                coordinates.set(
                    event.x.toInt() + screenPosition[0],
                    event.y.toInt() + screenPosition[1]
                )
            }
            false
        }
        setOnClickListener { action.invoke(coordinates) }
    }
}