package com.jayasuryat.basedata.mappers


public open class Mapper<in I : Any, out O : Any>(
    private val mappingStrategy: suspend (I) -> O,
) {

    @PublishedApi
    internal fun getMapper(): suspend (I) -> O = mappingStrategy
}

public suspend inline fun <reified I : Any, reified O : Any> Mapper<I, O>.map(input: I): O {

    try {
        return getMapper().invoke(input)
    } catch (ex: Exception) {
        val message = "Error mapping ${I::class.java} object to ${O::class.java} object"
        throw MapperException(message, ex)
    }
}

public suspend inline fun <reified I : Any, reified O : Any> Mapper<I, O>.map(input: List<I>): List<O> {

    try {
        val mapper = getMapper()
        return input.map { mapper(it) }
    } catch (ex: Exception) {
        val message = "Error mapping ${I::class.java} object to ${O::class.java} object"
        throw MapperException(message, ex)
    }
}