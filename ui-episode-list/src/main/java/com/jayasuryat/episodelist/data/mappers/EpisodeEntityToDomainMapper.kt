package com.jayasuryat.episodelist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.episodelist.domain.models.Episode


internal class EpisodeEntityToDomainMapper :
    Mapper<EpisodeEntity, Episode>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: EpisodeEntity,
        ): Episode = Episode(
            id = input.id,
            episode = input.episode,
            name = input.name,
        )
    }
}