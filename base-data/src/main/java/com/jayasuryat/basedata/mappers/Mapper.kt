package com.jayasuryat.basedata.mappers

public interface Mapper<in I, out O> {

    public suspend operator fun invoke(input: I): O

    public suspend operator fun invoke(input: List<I>): List<O> =
        input.map { model -> this.invoke(model) }
}