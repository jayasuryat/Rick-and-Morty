package com.jayasuryat.basedata.models

public sealed class KResult<T> {

    public data class Success<T> internal constructor(
        val data: T
    ) : KResult<T>()

    public data class Error<T> internal constructor(
        val throwable: Throwable,
        val errorMessage: String?,
    ) : KResult<T>()

    public fun getOrNull(): T? = when (this) {
        is Error -> null
        is Success -> data
    }

    public fun getOrThrow(): T = when (this) {
        is Error -> throw throwable
        is Success -> data
    }

    public fun exceptionOrNull(): Throwable? = when (this) {
        is Error -> throwable
        is Success -> null
    }

    override fun toString(): String = when (this) {
        is Error -> "KResult.Error(errorMessage:${errorMessage}, throwable:${throwable})"
        is Success -> "KResult.Success(data:${data.toString()})"
    }

    public companion object {

        public fun <T> success(data: T): Success<T> = Success(data = data)

        public fun <T> error(
            throwable: Throwable,
            message: String? = null,
        ): Error<T> = Error(throwable = throwable, errorMessage = message)
    }
}