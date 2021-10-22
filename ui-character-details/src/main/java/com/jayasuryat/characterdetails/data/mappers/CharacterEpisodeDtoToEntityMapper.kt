package com.jayasuryat.characterdetails.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.CharacterEpisodeQuery
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity

internal class CharacterEpisodeDtoToEntityMapper :
    Mapper<CharacterEpisodeQuery.Episode, EpisodeEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: CharacterEpisodeQuery.Episode,
        ): EpisodeEntity = EpisodeEntity(
            id = input.id?.toLong()!!,
            name = input.name!!,
            episode = input.episode!!,
        )
    }
}