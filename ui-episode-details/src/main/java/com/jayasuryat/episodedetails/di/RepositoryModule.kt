package com.jayasuryat.episodedetails.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodedetails.data.mappers.EpisodeDetailsDtoToEntityMapper
import com.jayasuryat.episodedetails.data.mappers.EpisodeDetailsEntityToDomainMapper
import com.jayasuryat.episodedetails.data.repositories.EpisodeDetailsRepo
import com.jayasuryat.episodedetails.data.sources.local.definitions.EpisodeDetailsLocalDataSource
import com.jayasuryat.episodedetails.data.sources.remote.definitions.EpisodeDetailsRemoteDataSource
import com.jayasuryat.episodedetails.domain.repositories.EpisodeDetailsRepository
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
        networkDataSource: EpisodeDetailsRemoteDataSource,
        localDataSource: EpisodeDetailsLocalDataSource,
    ): EpisodeDetailsRepository = EpisodeDetailsRepo(
        dispatcher = dispatcherProvider,
        remoteClient = networkDataSource,
        cacheClient = localDataSource,
        episodeDtoToEntityMapper = EpisodeDetailsDtoToEntityMapper(),
        episodeEntityToDomainMapper = EpisodeDetailsEntityToDomainMapper(),
    )
}