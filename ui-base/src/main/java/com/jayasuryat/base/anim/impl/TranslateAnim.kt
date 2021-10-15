package com.jayasuryat.base.anim.impl

import android.view.View
import android.view.animation.Interpolator
import android.view.animation.TranslateAnimation
import com.jayasuryat.base.anim.definitons.AnimBuilder
import com.jayasuryat.base.anim.definitons.InvokableAnimation
import java.lang.ref.WeakReference

public class TranslateAnim private constructor() {

    public class Builder internal constructor() : AnimBuilder() {

        private var startX: Float = 0f
        private var startY: Float = 0f

        private var endX: Float = 0f
        private var endY: Float = 0f

        private var interpolator: Interpolator = defaultInterpolator
        private var duration: Long = defaultDuration

        public fun fromCurrentPosition(): Builder {
            startX = 0f
            startY = 0f
            return this
        }

        public fun fromVerticalDelta(delta: Float): Builder {
            startX = 0f
            startY = delta
            return this
        }

        public fun fromHorizontalDelta(delta: Float): Builder {
            startX = delta
            startY = 0f
            return this
        }

        public fun toCurrentPosition(): Builder {
            endX = 0f
            endY = 0f
            return this
        }

        public fun toVerticalDelta(delta: Float): Builder {
            endX = 0f
            endY = delta
            return this
        }

        public fun toHorizontalDelta(delta: Float): Builder {
            endX = delta
            endY = 0f
            return this
        }

        public fun setDuration(duration: Long): Builder {
            this.duration = duration
            return this
        }

        public fun setInterpolator(interpolator: Interpolator): Builder {
            this.interpolator = interpolator
            return this
        }

        public override fun build(vararg forViews: View): InvokableAnimation {

            val animation = TranslateAnimation(startX, endX, startY, endY).apply {
                duration = this@Builder.duration
                interpolator = this@Builder.interpolator
            }

            val views = forViews.map(::WeakReference)

            return InvokableTranslateAnimation(animation, views)
        }

        private class InvokableTranslateAnimation(
            private val animation: TranslateAnimation,
            private val views: List<WeakReference<View>>,
            override var duration: Long = animation.duration,
            override var interpolator: Interpolator = animation.interpolator
        ) : InvokableAnimation() {

            public override fun start() {

                val views = views.mapNotNull { it.get() }
                if (views.isNullOrEmpty()) return

                animation.apply {
                    this.duration = duration
                    this.interpolator = interpolator
                }

                views.forEach { view -> view.startAnimation(animation) }
            }
        }
    }

    public companion object {

        public fun builder(): Builder = Builder()
    }
}