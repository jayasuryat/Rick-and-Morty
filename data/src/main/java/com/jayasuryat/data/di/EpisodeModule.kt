package com.jayasuryat.data.di

import com.jayasuryat.data.data.local.definitions.EpisodesLocalDataSource
import com.jayasuryat.data.data.local.entities.EpisodeEntity
import com.jayasuryat.data.data.local.impl.CacheClient
import com.jayasuryat.data.data.remote.definitions.EpisodesRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.EpisodeDto
import com.jayasuryat.data.data.remote.impl.NetworkClient
import com.jayasuryat.data.datasources.definitions.EpisodesRepository
import com.jayasuryat.data.datasources.impl.EpisodesRepoImpl
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.datasources.mappers.impl.episode.EpisodeDtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.impl.episode.EpisodeEntityToDomainMapper
import com.jayasuryat.data.models.domain.Episode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object EpisodeModule {

    @Singleton
    @Provides
    internal fun providesEpisodesLocalDataSource(
        cacheClient: CacheClient,
    ): EpisodesLocalDataSource = cacheClient.cacheDao()

    @Singleton
    @Provides
    internal fun providesEpisodesRemoteDataSource(
        cacheDao: NetworkClient,
    ): EpisodesRemoteDataSource = cacheDao


    @Singleton
    @Provides
    internal fun providesEpisodeDtoToEntityMapper():
            DtoToEntityMapper<EpisodeDto, EpisodeEntity> = EpisodeDtoToEntityMapper()

    @Singleton
    @Provides
    internal fun providesEpisodeEntityToDomainMapper():
            EntityToDomainMapper<EpisodeEntity, Episode> = EpisodeEntityToDomainMapper()

    @Singleton
    @Provides
    internal fun providesEpisodeRepository(
        networkClient: EpisodesRemoteDataSource,
        cacheClient: EpisodesLocalDataSource,
        EpisodeDtoToEntity: DtoToEntityMapper<EpisodeDto, EpisodeEntity>,
        EpisodeEntityToDomain: EntityToDomainMapper<EpisodeEntity, Episode>
    ): EpisodesRepository = EpisodesRepoImpl(
        networkClient = networkClient,
        cacheClient = cacheClient,
        episodeDtoToEntity = EpisodeDtoToEntity,
        episodeEntityToDomain = EpisodeEntityToDomain,
    )
}