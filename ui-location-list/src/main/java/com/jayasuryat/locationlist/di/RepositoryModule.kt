package com.jayasuryat.locationlist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationlist.LocationListQuery
import com.jayasuryat.locationlist.data.mappers.LocationDtoToEntityMapper
import com.jayasuryat.locationlist.data.mappers.LocationEntityToDomainMapper
import com.jayasuryat.locationlist.data.repos.LocationListRemoteMediator
import com.jayasuryat.locationlist.data.repos.LocationListRepo
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity
import com.jayasuryat.locationlist.data.sources.remote.definitions.LocationListRemoteDataSource
import com.jayasuryat.locationlist.di.MapperModule.L_DTO_TO_ENTITY
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.locationlist.domain.repository.LocationListRepository
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
    fun providesRemoteMediator(
        networkDataSource: LocationListRemoteDataSource,
        localDataSource: LocationListLocalDataSource,
        /* @Named(L_DTO_TO_ENTITY)
         locationDtoToEntityMapper:
         Mapper<@JvmSuppressWildcards LocationListQuery.Result, @JvmSuppressWildcards LocationEntity>,*/
    ): LocationListRemoteMediator = LocationListRemoteMediator(
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        locationDtoToEntityMapper =  LocationDtoToEntityMapper()
    )

    @Provides
    @Singleton
    fun test(
        remoteMediator: LocationListRemoteMediator,
        localDataSource: LocationListLocalDataSource,
        /*@Named(MapperModule.L_ENTITY_TO_DOMAIN)
        locationEntityToDomainMapper:
        Mapper<@JvmSuppressWildcards LocationEntity, @JvmSuppressWildcards Location>,*/
    ): LocationListRepository = LocationListRepo(
        mediator = remoteMediator,
        cacheClient = localDataSource,
        locationEntityToDomainMapper = LocationEntityToDomainMapper(),
    )
}