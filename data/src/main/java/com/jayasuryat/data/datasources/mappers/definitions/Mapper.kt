package com.jayasuryat.data.datasources.mappers.definitions

internal fun interface Mapper<in I, out O> {
    suspend operator fun invoke(input: I): O
}