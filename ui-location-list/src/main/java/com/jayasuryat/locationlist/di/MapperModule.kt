package com.jayasuryat.locationlist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationlist.LocationListQuery
import com.jayasuryat.locationlist.data.mappers.LocationDtoToEntityMapper
import com.jayasuryat.locationlist.data.mappers.LocationEntityToDomainMapper
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity
import com.jayasuryat.locationlist.domain.model.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    internal const val L_DTO_TO_ENTITY: String = "LocationDtoToEntity"
    internal const val L_ENTITY_TO_DOMAIN: String = "LocationEntityToDomain"

    @Provides
    @Singleton
    @Named(L_DTO_TO_ENTITY)
    internal fun providesLocationDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards LocationListQuery.Result,
                    @JvmSuppressWildcards LocationEntity> = LocationDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(L_ENTITY_TO_DOMAIN)
    internal fun providesLocationEntityToDtoMapper():
            Mapper<@JvmSuppressWildcards LocationEntity,
                    @JvmSuppressWildcards Location> = LocationEntityToDomainMapper()
}