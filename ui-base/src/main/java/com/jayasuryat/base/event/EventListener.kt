package com.jayasuryat.base.event

public interface EventListener<out T : Event> {

    public fun onEvent(event: Event)
}