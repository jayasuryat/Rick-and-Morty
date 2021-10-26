package com.jayasuryat.episodedetails.data.repositories

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodedetails.EpisodeDetailQuery
import com.jayasuryat.episodedetails.data.sources.local.definitions.EpisodeDetailsLocalDataSource
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity
import com.jayasuryat.episodedetails.data.sources.remote.definitions.EpisodeDetailsRemoteDataSource
import com.jayasuryat.episodedetails.domain.model.EpisodeDetails
import com.jayasuryat.episodedetails.domain.repositories.EpisodeDetailsRepository

internal class EpisodeDetailsRepo(
    private val dispatcher: DispatcherProvider,
    private val cacheClient: EpisodeDetailsLocalDataSource,
    private val remoteClient: EpisodeDetailsRemoteDataSource,
    private val episodeEntityToDomainMapper: Mapper<EpisodeDetailsEntity, EpisodeDetails>,
    private val episodeDtoToEntityMapper: Mapper<EpisodeDetailQuery.Episode, EpisodeDetailsEntity>,
) : EpisodeDetailsRepository {

    override suspend fun getEpisodeDetails(
        episodeId: Long,
    ): KResult<EpisodeDetails> = wrapAsResult(dispatcher.io()) {

        cacheClient.getEpisodeDetails(episodeId)?.let { cachedEpisode ->
            return@wrapAsResult episodeEntityToDomainMapper.map(cachedEpisode)
        }

        val remoteEpisode = remoteClient.getEpisodeDetails(episodeId).data?.episode
            ?: throw RuntimeException("Remote episode details with id $episodeId not found")

        cacheClient.saveEpisodeDetails(episodeDtoToEntityMapper.map(remoteEpisode))

        val cachedEpisode = cacheClient.getEpisodeDetails(episodeId)
            ?: throw RuntimeException("Cache episode details with id $episodeId not found")

        episodeEntityToDomainMapper.map(cachedEpisode)
    }
}