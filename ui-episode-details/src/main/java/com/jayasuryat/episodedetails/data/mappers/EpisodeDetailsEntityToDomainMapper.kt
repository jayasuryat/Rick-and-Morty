package com.jayasuryat.episodedetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.domain.model.EpisodeDetails

internal class EpisodeDetailsEntityToDomainMapper :
    Mapper<EpisodeDetailsEntity, EpisodeDetails>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: EpisodeDetailsEntity,
        ): EpisodeDetails = EpisodeDetails(
            id = input.id,
            name = input.name,
            airDate = input.airDate,
            episode = input.episode,
            characters = input.characters.map { character ->
                Character(
                    id = character.id,
                    name = character.name,
                    image = character.image,
                )
            },
        )
    }
}