package com.jayasuryat.base.event

@PublishedApi
internal class NoOpEventListener<T : Event> : EventListener<T> {
    override fun onEvent(event: Event) = Unit
}

public inline fun <reified T : Event> noOpEventListener(): EventListener<T> = NoOpEventListener()
