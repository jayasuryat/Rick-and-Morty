package com.jayasuryat.base.anim.definitons

import android.view.animation.Interpolator

public abstract class InvokableAnimation {

    internal abstract var duration: Long
    internal abstract var interpolator: Interpolator

    internal abstract fun start()
}