package com.jayasuryat.characterlist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.domain.models.Character

internal class CharacterEntityToDomainMapper : Mapper<CharacterEntity, Character> {

    override suspend fun invoke(input: CharacterEntity): Character = Character(
        id = input.id,
        name = input.name,
        imageUrl = input.imageUrl,
    )
}