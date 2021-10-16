package com.jayasuryat.characterlist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.domain.models.Character

internal class CharacterEntityToDomainMapper :
    Mapper<CharacterEntity, Character>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterEntity,
        ): Character = Character(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl,
        )
    }
}