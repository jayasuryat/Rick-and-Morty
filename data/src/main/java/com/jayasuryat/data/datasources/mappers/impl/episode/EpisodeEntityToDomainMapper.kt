package com.jayasuryat.data.datasources.mappers.impl.episode

import com.jayasuryat.data.data.local.entities.EpisodeEntity
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.models.domain.Episode

internal class EpisodeEntityToDomainMapper : EntityToDomainMapper<EpisodeEntity, Episode> {

    override suspend fun invoke(input: EpisodeEntity): Episode = with(input) {

        return Episode(
            id = id,
            name = name,
            airDate = airDate,
            episode = episode,
            characters = characters,
            url = url,
            created = created,
        )
    }
}