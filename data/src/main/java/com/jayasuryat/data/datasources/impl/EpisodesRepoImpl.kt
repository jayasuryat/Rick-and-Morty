package com.jayasuryat.data.datasources.impl

import com.jayasuryat.data.data.local.definitions.EpisodesLocalDataSource
import com.jayasuryat.data.data.local.entities.EpisodeEntity
import com.jayasuryat.data.data.remote.definitions.EpisodesRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.EpisodeDto
import com.jayasuryat.data.datasources.definitions.EpisodesRepository
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Episode
import com.jayasuryat.data.models.wrapAsResult

internal class EpisodesRepoImpl(
    private val networkClient: EpisodesRemoteDataSource,
    private val cacheClient: EpisodesLocalDataSource,
    private val episodeDtoToEntity: DtoToEntityMapper<EpisodeDto, EpisodeEntity>,
    private val episodeEntityToDomain: EntityToDomainMapper<EpisodeEntity, Episode>,
) : EpisodesRepository {

    override suspend fun getEpisodes(page: Int): KResult<List<Episode>> = wrapAsResult {

        networkClient.getEpisodes(page)
            .results
            .map { dto -> episodeDtoToEntity(dto) }
            .also { entities -> cacheClient.saveEpisodes(entities) }

        cacheClient.getAllEpisodes()
            .map { entity -> episodeEntityToDomain(entity) }
    }

    override suspend fun getAllEpisodes(): KResult<List<Episode>> = wrapAsResult {

        val totalCount = networkClient.getEpisodes(0).info.count

        getEpisodes((1..totalCount).toList())
            .getOrThrow()
    }

    override suspend fun getEpisodeFromCache(episodeId: Long): KResult<Episode> = wrapAsResult {
        episodeEntityToDomain(cacheClient.getEpisodeForId(episodeId))
    }

    override suspend fun getAllEpisodesInCache(): KResult<List<Episode>> = wrapAsResult {

        cacheClient.getAllEpisodes()
            .map { entity -> episodeEntityToDomain(entity) }
    }

    @Suppress("ComplexRedundantLet")
    override suspend fun getEpisodes(episodes: List<Long>): KResult<List<Episode>> = wrapAsResult {

        val cachedEpisodes = cacheClient.getEpisodesFor(episodes)
        if (episodes.size == cachedEpisodes.size)
            return@wrapAsResult episodeEntityToDomain(cachedEpisodes)

        val unCachedEpisodes = episodes.filter { episode ->
            cachedEpisodes.find { it.id == episode } == null
        }

        networkClient.getEpisodesFor(unCachedEpisodes)
            .let { dtoList -> episodeDtoToEntity(dtoList) }
            .let { entityList -> cacheClient.saveEpisodes(entityList) }

        cacheClient.getEpisodesFor(episodes)
            .let { entityList -> episodeEntityToDomain(entityList) }
    }
}