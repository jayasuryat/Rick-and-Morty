package com.jayasuryat.episodedetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodedetails.EpisodeDetailQuery
import com.jayasuryat.episodedetails.data.sources.local.entities.Character
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity

internal class EpisodeDetailsDtoToEntityMapper :
    Mapper<EpisodeDetailQuery.Episode, EpisodeDetailsEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: EpisodeDetailQuery.Episode,
        ): EpisodeDetailsEntity = EpisodeDetailsEntity(
            id = input.id!!.toLong(),
            name = input.name!!,
            airDate = input.air_date!!,
            episode = input.episode!!,
            characters = input.characters.filterNotNull().map { character ->
                Character(
                    id = character.id!!.toLong(),
                    name = character.name!!,
                    image = character.image!!,
                )
            },
        )
    }
}