package com.jayasuryat.episodelist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodelist.EpisodeListQuery
import com.jayasuryat.episodelist.data.repos.EpisodeListRepo
import com.jayasuryat.episodelist.data.sources.local.definitions.EpisodeListLocalDataSource
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.episodelist.data.sources.remote.definitions.EpisodeListRemoteDataSource
import com.jayasuryat.episodelist.di.MapperModule.E_L_DTO_TO_ENTITY
import com.jayasuryat.episodelist.di.MapperModule.E_L_ENTITY_TO_DOMAIN
import com.jayasuryat.episodelist.domain.models.Episode
import com.jayasuryat.episodelist.domain.repos.EpisodeListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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

        @Named(E_L_DTO_TO_ENTITY)
        episodeDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards EpisodeListQuery.EpisodesById, @JvmSuppressWildcards EpisodeEntity>,

        @Named(E_L_ENTITY_TO_DOMAIN)
        episodeEntityToDomainMapper:
        Mapper<@JvmSuppressWildcards EpisodeEntity, @JvmSuppressWildcards Episode>,
    ): EpisodeListRepository = EpisodeListRepo(
        dispatcher = dispatcherProvider,
        remoteClient = networkDataSource,
        cacheClient = localDataSource,
        episodeDtoToEntityMapper = episodeDtoToEntityMapper,
        episodeEntityToDomainMapper = episodeEntityToDomainMapper,
    )
}