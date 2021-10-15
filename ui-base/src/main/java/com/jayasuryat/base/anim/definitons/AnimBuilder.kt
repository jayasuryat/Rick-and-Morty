package com.jayasuryat.base.anim.definitons

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator

public abstract class AnimBuilder {

    internal val defaultInterpolator: Interpolator by lazy { DecelerateInterpolator() }

    internal val defaultDuration: Long by lazy { 300 }

    internal abstract fun build(vararg forViews: View): InvokableAnimation
}