package com.jayasuryat.characterdetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.CharacterEpisodeQuery
import com.jayasuryat.characterdetails.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeEntityToDomainMapper
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Episode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    internal const val C_D_DTO_TO_ENTITY: String = "CharacterDetailsDtoToEntity"
    internal const val C_D_ENTITY_TO_DOMAIN: String = "CharacterDetailsEntityToDomain"
    internal const val C_E_DTO_TO_ENTITY: String = "CharacterEpisodeDtoToEntity"
    internal const val C_E_ENTITY_TO_DOMAIN: String = "CharacterEpisodeEntityToDomain"

  /*  @Provides
    @Singleton
    @Named(C_D_DTO_TO_ENTITY)
    internal fun providesCharacterDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards CharacterDetailsQuery.Character,
                    @JvmSuppressWildcards CharacterEntity> = CharacterDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(C_D_ENTITY_TO_DOMAIN)
    internal fun providesCharacterEntityToDtoMapper():
            Mapper<@JvmSuppressWildcards CharacterEntity,
                    @JvmSuppressWildcards CharacterDetails> = CharacterEntityToDomainMapper()

    @Provides
    @Singleton
    @Named(C_E_DTO_TO_ENTITY)
    internal fun provideCharacterEpisodeDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards CharacterEpisodeQuery.Episode,
                    @JvmSuppressWildcards EpisodeEntity> =
        CharacterEpisodeDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(C_E_ENTITY_TO_DOMAIN)
    internal fun provideCharacterEpisodeEntityToDomainMapper():
            Mapper<@JvmSuppressWildcards EpisodeEntity,
                    @JvmSuppressWildcards Episode> = CharacterEpisodeEntityToDomainMapper()*/
}