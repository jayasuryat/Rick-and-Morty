package com.jayasuryat.locationlist.di

import com.jayasuryat.locationlist.data.mappers.LocationDtoToEntityMapper
import com.jayasuryat.locationlist.data.mappers.LocationEntityToDomainMapper
import com.jayasuryat.locationlist.data.repos.LocationListRemoteMediator
import com.jayasuryat.locationlist.data.repos.LocationListRepo
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.remote.definitions.LocationListRemoteDataSource
import com.jayasuryat.locationlist.domain.repository.LocationListRepository
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
    fun providesRemoteMediator(
        networkDataSource: LocationListRemoteDataSource,
        localDataSource: LocationListLocalDataSource,
    ): LocationListRemoteMediator = LocationListRemoteMediator(
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        locationDtoToEntityMapper = LocationDtoToEntityMapper()
    )

    @Provides
    @Singleton
    fun test(
        remoteMediator: LocationListRemoteMediator,
        localDataSource: LocationListLocalDataSource,
    ): LocationListRepository = LocationListRepo(
        mediator = remoteMediator,
        cacheClient = localDataSource,
        locationEntityToDomainMapper = LocationEntityToDomainMapper(),
    )
}