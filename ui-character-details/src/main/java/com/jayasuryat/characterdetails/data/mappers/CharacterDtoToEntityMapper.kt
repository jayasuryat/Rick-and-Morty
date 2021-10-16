package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.Episode
import com.jayasuryat.characterdetails.data.sources.local.entities.Location

internal class CharacterDtoToEntityMapper :
    Mapper<CharacterDetailsQuery.Character, CharacterEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterDetailsQuery.Character,
        ): CharacterEntity = CharacterEntity(
            id = input.id()!!,
            name = input.name()!!,
            status = input.status()!!,
            species = input.species()!!,
            type = input.type()!!,
            gender = input.gender()!!,
            location = Location(
                id = input.location()!!.id()!!,
                name = input.location()!!.name()!!,
                type = input.location()!!.type()!!,
                dimension = input.location()!!.dimension()!!,
            ),
            image = input.image()!!,
            episode = input.episode().map { episode ->
                Episode(
                    id = episode.id()!!.toLong(),
                    name = episode.name()!!,
                    episode = episode.episode()!!,
                )
            }
        )
    }
}