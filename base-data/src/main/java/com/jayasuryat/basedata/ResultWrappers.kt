package com.jayasuryat.basedata

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

public suspend inline fun <T> wrapAsResult(
    logic: suspend () -> T,
): KResult<T> {

    val result = runCatching { logic() }

    return result
        .exceptionOrNull()
        ?.let { ex -> KResult.error(throwable = ex) }
        ?: run { KResult.success(result.getOrThrow()) }
}

public suspend inline fun <T> wrapAsResult(
    coroutineDispatcher: CoroutineDispatcher,
    crossinline logic: suspend () -> T,
): KResult<T> = withContext(coroutineDispatcher) { wrapAsResult(logic) }
