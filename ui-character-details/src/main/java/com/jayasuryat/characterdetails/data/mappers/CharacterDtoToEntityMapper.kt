package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.Location

internal class CharacterDtoToEntityMapper :
    Mapper<CharacterDetailsQuery.Character, CharacterEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterDetailsQuery.Character,
        ): CharacterEntity = CharacterEntity(
            id = input.id!!,
            name = input.name!!,
            image = input.image!!,
            status = input.status!!,
            species = input.species!!,
            type = input.type!!,
            gender = input.gender!!,
            location = Location(
                id = input.location?.id,
                name = input.location?.name,
                type = input.location?.type,
                dimension = input.location?.dimension,
            ),
            origin = Location(
                id = input.origin?.id,
                name = input.origin?.name,
                type = input.origin?.type,
                dimension = input.origin?.dimension,
            ),
        )
    }
}