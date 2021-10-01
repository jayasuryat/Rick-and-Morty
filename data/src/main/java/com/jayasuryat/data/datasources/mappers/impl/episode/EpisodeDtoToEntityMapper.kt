package com.jayasuryat.data.datasources.mappers.impl.episode

import com.jayasuryat.data.data.local.entities.EpisodeEntity
import com.jayasuryat.data.data.remote.dtos.EpisodeDto
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper

internal class EpisodeDtoToEntityMapper : DtoToEntityMapper<EpisodeDto, EpisodeEntity> {

    override suspend fun invoke(input: EpisodeDto): EpisodeEntity = with(input) {

        return EpisodeEntity(
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