package com.jayasuryat.characterlist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity

internal class CharacterDtoToEntityMapper :
    Mapper<CharacterListQuery.Result, CharacterEntity> {

    override suspend fun invoke(
        input: CharacterListQuery.Result
    ): CharacterEntity = CharacterEntity(
        id = input.id()!!.toLong(),
        name = input.name()!!,
        imageUrl = input.image()!!,
    )
}