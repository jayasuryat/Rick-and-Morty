package com.jayasuryat.episodedetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodedetails.EpisodeDetailQuery
import com.jayasuryat.episodedetails.data.mappers.EpisodeDetailsDtoToEntityMapper
import com.jayasuryat.episodedetails.data.mappers.EpisodeDetailsEntityToDomainMapper
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity
import com.jayasuryat.episodedetails.domain.model.EpisodeDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    internal const val E_D_DTO_TO_ENTITY: String = "EpisodeDetailsDtoToEntity"
    internal const val E_D_ENTITY_TO_DOMAIN: String = "EpisodeDetailsEntityToDomain"

  /*  @Provides
    @Singleton
    @Named(E_D_DTO_TO_ENTITY)
    internal fun providesCharacterDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards EpisodeDetailQuery.Episode,
                    @JvmSuppressWildcards EpisodeDetailsEntity> =
        EpisodeDetailsDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(E_D_ENTITY_TO_DOMAIN)
    internal fun providesCharacterEntityToDtoMapper():
            Mapper<@JvmSuppressWildcards EpisodeDetailsEntity,
                    @JvmSuppressWildcards EpisodeDetails> =
        EpisodeDetailsEntityToDomainMapper()*/
}