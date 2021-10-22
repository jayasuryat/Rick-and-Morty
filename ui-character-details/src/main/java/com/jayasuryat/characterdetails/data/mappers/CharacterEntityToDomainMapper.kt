package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Location

internal class CharacterEntityToDomainMapper :
    Mapper<CharacterEntity, CharacterDetails>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterEntity,
        ): CharacterDetails = CharacterDetails(
            id = input.id,
            name = input.name,
            image = input.image,
            status = CharacterDetails.Status.enumFrom(input.status),
            species = CharacterDetails.Species.enumFrom(input.species),
            type = input.type,
            gender = CharacterDetails.Gender.enumFrom(input.gender),
            location = Location(
                id = input.location.id,
                name = input.location.name,
                type = input.location.type,
                dimension = input.location.dimension,
            ),
            origin = Location(
                id = input.origin.id,
                name = input.origin.name,
                type = input.origin.type,
                dimension = input.origin.dimension,
            ),
        )
    }
}