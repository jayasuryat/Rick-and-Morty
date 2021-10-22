package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.characterdetails.domain.models.Episode

internal class CharacterEpisodeEntityToDomainMapper :
    Mapper<EpisodeEntity, Episode>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: EpisodeEntity,
        ): Episode = Episode(
            id = input.id,
            name = input.name,
            episode = input.episode,
        )
    }
}