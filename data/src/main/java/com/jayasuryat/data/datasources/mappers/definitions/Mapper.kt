package com.jayasuryat.data.datasources.mappers.definitions

internal interface Mapper<in I, out O> {

    suspend operator fun invoke(input: I): O

    suspend operator fun invoke(input: List<I>): List<O> =
        input.map { model -> this.invoke(model) }
}