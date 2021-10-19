package com.jayasuryat.episodelist.data.repos

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodelist.EpisodeListQuery
import com.jayasuryat.episodelist.data.sources.local.definitions.EpisodeListLocalDataSource
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.episodelist.data.sources.remote.definitions.EpisodeListRemoteDataSource
import com.jayasuryat.episodelist.domain.models.Episode
import com.jayasuryat.episodelist.domain.repos.EpisodeListRepository

internal class EpisodeListRepo(
    private val dispatcher: DispatcherProvider,
    private val cacheClient: EpisodeListLocalDataSource,
    private val remoteClient: EpisodeListRemoteDataSource,
    private val episodeDtoToEntityMapper: Mapper<EpisodeListQuery.EpisodesById, EpisodeEntity>,
    private val episodeEntityToDomainMapper: Mapper<EpisodeEntity, Episode>,
) : EpisodeListRepository {

    override suspend fun getAllEpisodes(): KResult<List<Episode>> = wrapAsResult(dispatcher.io()) {

        val episodesCount = remoteClient.getTotalEpisodesCount().data
            ?.episodes()?.info()?.count() ?: 0

        val cachedEpisodes = cacheClient.getAllEpisodes()

        if (!cachedEpisodes.isNullOrEmpty() && cachedEpisodes.size == episodesCount)
            return@wrapAsResult episodeEntityToDomainMapper.map(cachedEpisodes)

        val episodes = (1..episodesCount).map { it.toLong() }
        val remoteEpisodes = remoteClient.getEpisodes(episodes).data?.episodesByIds()?.toList()

        if (remoteEpisodes.isNullOrEmpty()) throw RuntimeException("No episodes found")

        val mappedEpisodes = episodeDtoToEntityMapper.map(remoteEpisodes)
        cacheClient.saveEpisodes(mappedEpisodes)

        val newCachedEpisodes = cacheClient.getAllEpisodes()
        if (newCachedEpisodes.isNullOrEmpty()) throw IllegalStateException("No data found in cache")
        episodeEntityToDomainMapper.map(newCachedEpisodes)
    }

    override suspend fun getAllEpisodesInCache(): KResult<List<Episode>> =
        wrapAsResult(dispatcher.io()) {
            val cachedEpisodes = cacheClient.getAllEpisodes()
            if (cachedEpisodes.isNullOrEmpty()) throw IllegalStateException("No data found in cache")
            episodeEntityToDomainMapper.map(cachedEpisodes)
        }
}