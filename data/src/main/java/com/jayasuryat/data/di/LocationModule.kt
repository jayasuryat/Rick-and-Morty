package com.jayasuryat.data.di

import com.jayasuryat.data.data.local.definitions.LocationLocalDataSource
import com.jayasuryat.data.data.local.impl.CacheClient
import com.jayasuryat.data.data.remote.definitions.LocationsRemoteDataSource
import com.jayasuryat.data.data.remote.impl.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocationModule {

    @Singleton
    @Provides
    internal fun providesLocationsLocalDataSource(
        cacheClient: CacheClient,
    ): LocationLocalDataSource = cacheClient.cacheDao()

    @Singleton
    @Provides
    internal fun providesLocationsRemoteDataSource(
        cacheDao: NetworkClient,
    ): LocationsRemoteDataSource = cacheDao

    /*
    @Singleton
    @Provides
    internal fun providesLocationDtoToEntityMapper():
            DtoToEntityMapper<LocationDto, LocationEntity> = LocationDtoToEntityMapper()

    @Singleton
    @Provides
    internal fun providesLocationEntityToDomainMapper():
            EntityToDomainMapper<LocationEntity, Location> = LocationEntityToDomainMapper()

    @Singleton
    @Provides
    internal fun providesLocationRepository(
        networkClient: LocationsRemoteDataSource,
        cacheClient: LocationsLocalDataSource,
        LocationDtoToEntity: DtoToEntityMapper<LocationDto, LocationEntity>,
        LocationEntityToDomain: EntityToDomainMapper<LocationEntity, Location>
    ): LocationsRepository = LocationsRepoImpl(
        networkClient = networkClient,
        cacheClient = cacheClient,
        LocationDtoToEntity = LocationDtoToEntity,
        LocationEntityToDomain = LocationEntityToDomain,
    )
    */
}