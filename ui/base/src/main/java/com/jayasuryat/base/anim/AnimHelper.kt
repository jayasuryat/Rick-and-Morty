package com.jayasuryat.base.anim

import android.view.animation.Interpolator
import com.jayasuryat.base.anim.definitons.ExecutableAnimation
import com.jayasuryat.base.anim.definitons.InvokableAnimation

public object AnimHelper {

    public fun create(logic: Builder.() -> Unit): ExecutableAnimation =
        Builder().apply(logic).build()

    public class Builder internal constructor() {

        private var animations: MutableList<InvokableAnimation> = mutableListOf()

        private var duration: Long? = null
        private var interpolator: Interpolator? = null

        public fun addAnim(builder: () -> InvokableAnimation): Builder {
            animations.add(builder())
            return this
        }

        public fun overrideDuration(duration: Long) {
            this.duration = duration
        }

        public fun overrideInterpolator(interpolator: Interpolator) {
            this.interpolator = interpolator
        }

        internal fun build(): ExecutableAnimation = ExecutableAnimation(animations)

        //TODO : Circular reveal anim
    }
}