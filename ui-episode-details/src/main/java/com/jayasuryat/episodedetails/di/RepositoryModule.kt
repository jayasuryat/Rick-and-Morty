package com.jayasuryat.episodedetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.episodedetails.EpisodeDetailQuery
import com.jayasuryat.episodedetails.data.repositories.EpisodeDetailsRepo
import com.jayasuryat.episodedetails.data.sources.local.definitions.EpisodeDetailsLocalDataSource
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity
import com.jayasuryat.episodedetails.data.sources.remote.definitions.EpisodeDetailsRemoteDataSource
import com.jayasuryat.episodedetails.di.MapperModule.E_D_DTO_TO_ENTITY
import com.jayasuryat.episodedetails.di.MapperModule.E_D_ENTITY_TO_DOMAIN
import com.jayasuryat.episodedetails.domain.model.EpisodeDetails
import com.jayasuryat.episodedetails.domain.repositories.EpisodeDetailsRepository
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
        networkDataSource: EpisodeDetailsRemoteDataSource,
        localDataSource: EpisodeDetailsLocalDataSource,

        @Named(E_D_DTO_TO_ENTITY)
        characterDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards EpisodeDetailQuery.Episode, @JvmSuppressWildcards EpisodeDetailsEntity>,

        @Named(E_D_ENTITY_TO_DOMAIN)
        characterEntityToDtoMapper:
        Mapper<@JvmSuppressWildcards EpisodeDetailsEntity, @JvmSuppressWildcards EpisodeDetails>,
    ): EpisodeDetailsRepository = EpisodeDetailsRepo(
        dispatcher = dispatcherProvider,
        remoteClient = networkDataSource,
        cacheClient = localDataSource,
        episodeDtoToEntityMapper = characterDtoToEntityMapper,
        episodeEntityToDomainMapper = characterEntityToDtoMapper,
    )
}