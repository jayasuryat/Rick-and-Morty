package com.jayasuryat.episodelist.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodelist.data.mappers.EpisodeDtoToEntityMapper
import com.jayasuryat.episodelist.data.mappers.EpisodeEntityToDomainMapper
import com.jayasuryat.episodelist.data.repos.EpisodeListRepo
import com.jayasuryat.episodelist.data.sources.local.definitions.EpisodeListLocalDataSource
import com.jayasuryat.episodelist.data.sources.remote.definitions.EpisodeListRemoteDataSource
import com.jayasuryat.episodelist.domain.repos.EpisodeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun providesCharacterListRepo(
        dispatcherProvider: DispatcherProvider,
        networkDataSource: EpisodeListRemoteDataSource,
        localDataSource: EpisodeListLocalDataSource,
    ): EpisodeListRepository = EpisodeListRepo(
        dispatcher = dispatcherProvider,
        remoteClient = networkDataSource,
        cacheClient = localDataSource,
        episodeDtoToEntityMapper = EpisodeDtoToEntityMapper(),
        episodeEntityToDomainMapper = EpisodeEntityToDomainMapper(),
    )
}