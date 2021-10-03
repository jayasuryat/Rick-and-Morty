package com.jayasuryat.base.anim.definitons

public class ExecutableAnimation(
    private val animations: List<InvokableAnimation>,
) {

    public fun start() {
        animations.forEach { it.start() }
    }
}