package com.jayasuryat.episodelist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.episodelist.EpisodeListQuery
import com.jayasuryat.episodelist.data.mappers.EpisodeDtoToEntityMapper
import com.jayasuryat.episodelist.data.mappers.EpisodeEntityToDomainMapper
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.episodelist.domain.models.Episode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    internal const val E_L_DTO_TO_ENTITY: String = "EpisodeListDtoToEntity"
    internal const val E_L_ENTITY_TO_DOMAIN: String = "EpisodeListEntityToDomain"

    @Provides
    @Singleton
    @Named(E_L_DTO_TO_ENTITY)
    internal fun providesCharacterDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards EpisodeListQuery.EpisodesById,
                    @JvmSuppressWildcards EpisodeEntity> =
        EpisodeDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(E_L_ENTITY_TO_DOMAIN)
    internal fun providesCharacterEntityToDomainMapper():
            Mapper<@JvmSuppressWildcards EpisodeEntity,
                    @JvmSuppressWildcards Episode> =
        EpisodeEntityToDomainMapper()
}