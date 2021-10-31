package com.jayasuryat.event

public fun interface EventListener<in T : Event> {

    public fun onEvent(event: T)
}