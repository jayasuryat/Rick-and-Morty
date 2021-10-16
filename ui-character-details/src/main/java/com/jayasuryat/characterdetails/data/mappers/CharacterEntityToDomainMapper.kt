package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Episode
import com.jayasuryat.characterdetails.domain.models.Location

internal class CharacterEntityToDomainMapper :
    Mapper<CharacterEntity, CharacterDetails>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterEntity,
        ): CharacterDetails = CharacterDetails(
            id = input.id,
            name = input.name,
            status = CharacterDetails.Status.valueOf(input.status),
            species = CharacterDetails.Species.valueOf(input.species),
            type = input.type,
            gender = CharacterDetails.Gender.valueOf(input.gender),
            location = Location(
                id = input.location.id,
                name = input.location.name,
                type = input.location.type,
                dimension = input.location.dimension,
            ),
            image = input.image,
            episode = input.episode.map { episode ->
                Episode(
                    id = episode.id,
                    name = episode.name,
                    episode = episode.episode,
                )
            }
        )
    }
}