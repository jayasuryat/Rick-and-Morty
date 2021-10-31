package com.jayasuryat.event

@PublishedApi
internal class NoOpEventListener<T : Event> : EventListener<T> {

    override fun onEvent(event: T) = Unit
}

public inline fun <reified T : Event> noOpEventListener(): EventListener<T> = NoOpEventListener()