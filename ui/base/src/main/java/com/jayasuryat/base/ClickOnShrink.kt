package com.jayasuryat.base

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator


public fun View.shrinkOnClick(onClickListener: () -> Unit) {
    this.setOnTouchListener(ClickOnShrink(this))
    this.setOnClickListener {
        onClickListener()
    }
}

private class ClickOnShrink(private val viewToShrink: View) : View.OnTouchListener {

    private val animInterpolator: TimeInterpolator by lazy { AccelerateInterpolator() }

    private val scaleDownX: ObjectAnimator by lazy {
        ObjectAnimator
            .ofFloat(viewToShrink, ANIM_PROP_SCALE_X, SHRINK_FACTOR)
            .apply {
                duration = ANIM_DURATION
                interpolator = animInterpolator
            }
    }

    private val scaleDownY: ObjectAnimator by lazy {
        ObjectAnimator
            .ofFloat(viewToShrink, ANIM_PROP_SCALE_Y, SHRINK_FACTOR)
            .apply {
                duration = ANIM_DURATION
                interpolator = animInterpolator
            }
    }

    private val scaleUpX: ObjectAnimator by lazy {
        ObjectAnimator
            .ofFloat(viewToShrink, ANIM_PROP_SCALE_X, 1f)
            .apply {
                duration = ANIM_DURATION
                interpolator = animInterpolator
            }
    }

    private val scaleUpY: ObjectAnimator by lazy {
        ObjectAnimator
            .ofFloat(viewToShrink, ANIM_PROP_SCALE_Y, 1f)
            .apply {
                duration = ANIM_DURATION
                interpolator = animInterpolator
            }
    }

    private val scaleDownAnim: AnimatorSet by lazy {
        AnimatorSet().apply { play(scaleDownX).with(scaleDownY) }
    }

    private val scaleUpAnim: AnimatorSet by lazy {
        AnimatorSet().apply { play(scaleUpX).with(scaleUpY) }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> scaleDownAnim.apply {
                start()
                //doOnEnd { scaleUpAnim.start() }
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> scaleUpAnim.start()
        }

        return false
    }

    companion object {

        private const val ANIM_PROP_SCALE_X: String = "scaleX"
        private const val ANIM_PROP_SCALE_Y: String = "scaleY"
        private const val ANIM_DURATION: Long = 60

        private const val SHRINK_FACTOR: Float = 0.75f
    }
}