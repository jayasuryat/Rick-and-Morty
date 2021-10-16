package com.jayasuryat.basedata.mappers

public class MapperException(
    override val message: String,
    cause: Throwable,
) : Exception(message, cause)