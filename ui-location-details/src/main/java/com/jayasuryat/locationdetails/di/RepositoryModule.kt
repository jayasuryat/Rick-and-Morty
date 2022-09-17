package com.jayasuryat.locationdetails.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.locationdetails.data.mappers.LocationDetailsDtoToEntityMapper
import com.jayasuryat.locationdetails.data.mappers.LocationDetailsEntityToDomainMapper
import com.jayasuryat.locationdetails.data.repos.LocationDetailsRepo
import com.jayasuryat.locationdetails.data.sources.local.definitions.LocationDetailsLocalDataSource
import com.jayasuryat.locationdetails.data.sources.remote.definitions.LocationDetailsRemoteDataSource
import com.jayasuryat.locationdetails.domain.repositories.LocationDetailsRepository
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
        networkDataSource: LocationDetailsRemoteDataSource,
        localDataSource: LocationDetailsLocalDataSource,

        /*  @Named(L_D_DTO_TO_ENTITY)
          locationDtoToEntityMapper:
          Mapper<@JvmSuppressWildcards LocationDetailsQuery.Location, @JvmSuppressWildcards LocationDetailsEntity>,

          @Named(L_D_ENTITY_TO_DOMAIN)
          locationEntityToDtoMapper:
          Mapper<@JvmSuppressWildcards LocationDetailsEntity, @JvmSuppressWildcards LocationDetails>,*/
    ): LocationDetailsRepository = LocationDetailsRepo(
        dispatcher = dispatcherProvider,
        remoteClient = networkDataSource,
        cacheClient = localDataSource,
        locationDtoToEntityMapper = LocationDetailsDtoToEntityMapper(),
        locationEntityToDomainMapper = LocationDetailsEntityToDomainMapper(),
    )
}