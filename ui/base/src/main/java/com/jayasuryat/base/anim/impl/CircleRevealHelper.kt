package com.jayasuryat.base.anim.impl

import android.animation.Animator
import android.graphics.Point
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import kotlin.math.hypot

public class CircleRevealHelper private constructor(
    public val animation: Animator
) {

    public fun start(): Unit = animation.start()

    public class Builder(private val forView: View) {

        private var startX: Double = 0.0
        private var startY: Double = 0.0

        private var endX: Double = 0.0
        private var endY: Double = 0.0

        private var duration: Long = 300
        private var interpolator: Interpolator = DecelerateInterpolator()

        public fun setStartPoint(x: Double, y: Double): Builder {
            startX = x
            startY = y
            return this
        }

        public fun setEndPoint(x: Double, y: Double): Builder {
            endX = x
            endY = y
            return this
        }

        public fun setFarthestPointFromStartAsEnd(): Builder {

            val startLeft = (startX < (forView.width.toDouble() / 2))
            val startTop = (startY < (forView.height.toDouble() / 2))

            val point = when {
                // top left
                startLeft && startTop -> Point(forView.width, forView.height)
                // top right
                !startLeft && startTop -> Point(0, forView.height)
                // left bottom
                startLeft && !startTop -> Point(forView.width, forView.height)
                // right bottom
                else -> Point(0, 0)
            }

            endX = point.x.toDouble()
            endY = point.y.toDouble()
            return this
        }

        public fun setDuration(mills: Long): Builder {
            duration = mills
            return this
        }

        public fun setInterpolator(interpolator: Interpolator): Builder {
            this.interpolator = interpolator
            return this
        }

        public fun build(): CircleRevealHelper {

            val xDiff = endX - startX
            val yDiff = endY - startY
            val radius = hypot(xDiff, yDiff)

            val anim = ViewAnimationUtils
                .createCircularReveal(
                    forView,
                    startX.toInt(),
                    startY.toInt(),
                    0f,
                    radius.toFloat()
                ).apply {
                    interpolator = this@Builder.interpolator
                    duration = this@Builder.duration
                }

            return CircleRevealHelper(animation = anim)
        }
    }
}