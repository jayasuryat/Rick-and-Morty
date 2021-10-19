package com.jayasuryat.base.anim.impl

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import com.jayasuryat.base.anim.definitons.AnimBuilder
import com.jayasuryat.base.anim.definitons.InvokableAnimation
import java.lang.ref.WeakReference

public class AlphaAnim private constructor() {

    public class Builder internal constructor() : AnimBuilder() {

        private var startAlpha: Float = 0f
        private var endAlpha: Float = 1f
        private var intermediateAlphaSteps: List<Float>? = null

        private var interpolator: Interpolator = defaultInterpolator
        private var duration: Long = defaultDuration

        public fun from(alpha: Float): Builder {
            startAlpha = alpha
            return this
        }

        public fun intermediateSteps(vararg alpha: Float): Builder {
            intermediateAlphaSteps = alpha.toList()
            return this
        }

        public fun to(alpha: Float): Builder {
            endAlpha = alpha
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

            val values = mutableListOf<Float>().apply {
                add(startAlpha)
                intermediateAlphaSteps?.let(::addAll)
                add(endAlpha)
            }.toFloatArray()

            val animBuilder: (View) -> ValueAnimator = { view ->
                ObjectAnimator
                    .ofFloat(view, "alpha", *values)
                    .apply {
                        duration = this@Builder.duration
                        interpolator = this@Builder.interpolator
                    }
            }

            val views = forViews.map(::WeakReference)

            return InvokableAlphaAnimation(
                animationBuilder = animBuilder,
                views = views,
                duration = duration,
                interpolator = interpolator,
            )
        }

        private class InvokableAlphaAnimation(
            private val animationBuilder: (View) -> ValueAnimator,
            private val views: List<WeakReference<View>>,
            override var duration: Long,
            override var interpolator: Interpolator,
        ) : InvokableAnimation() {

            public override fun start() {

                val views = views.mapNotNull { it.get() }
                if (views.isNullOrEmpty()) return

                views.map(animationBuilder).forEach {
                    it.apply {
                        this.duration = this@InvokableAlphaAnimation.duration
                        this.interpolator = this@InvokableAlphaAnimation.interpolator
                    }.start()
                }
            }
        }
    }

    public companion object {

        public fun builder(): Builder = Builder()
    }
}