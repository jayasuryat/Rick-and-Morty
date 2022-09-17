package com.jayasuryat.locationdetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationdetails.LocationDetailsQuery
import com.jayasuryat.locationdetails.data.mappers.LocationDetailsDtoToEntityMapper
import com.jayasuryat.locationdetails.data.mappers.LocationDetailsEntityToDomainMapper
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    internal const val L_D_DTO_TO_ENTITY: String = "LocationDetailsDtoToEntity"
    internal const val L_D_ENTITY_TO_DOMAIN: String = "LocationDetailsEntityToDomain"

  /*  @Provides
    @Singleton
    @Named(L_D_DTO_TO_ENTITY)
    internal fun providesLocationDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards LocationDetailsQuery.Location,
                    @JvmSuppressWildcards LocationDetailsEntity> =
        LocationDetailsDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(L_D_ENTITY_TO_DOMAIN)
    internal fun providesLocationEntityToDtoMapper():
            Mapper<@JvmSuppressWildcards LocationDetailsEntity,
                    @JvmSuppressWildcards LocationDetails> =
        LocationDetailsEntityToDomainMapper()*/
}