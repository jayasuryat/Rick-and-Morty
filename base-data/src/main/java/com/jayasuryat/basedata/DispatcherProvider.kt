package com.jayasuryat.basedata

import kotlinx.coroutines.CoroutineDispatcher

public interface DispatcherProvider {

    public fun ui(): CoroutineDispatcher
    public fun io(): CoroutineDispatcher
    public fun computation(): CoroutineDispatcher
}