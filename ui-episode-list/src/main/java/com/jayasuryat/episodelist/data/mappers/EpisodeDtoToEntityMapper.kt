package com.jayasuryat.episodelist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodelist.EpisodeListQuery
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity


internal class EpisodeDtoToEntityMapper :
    Mapper<EpisodeListQuery.EpisodesById, EpisodeEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: EpisodeListQuery.EpisodesById,
        ): EpisodeEntity = EpisodeEntity(
            id = input.id!!.toLong(),
            episode = input.episode!!,
            name = input.name!!,
        )
    }
}