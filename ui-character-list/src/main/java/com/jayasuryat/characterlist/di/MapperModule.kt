package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterlist.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.domain.models.Character
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

   /* @Provides
    @Singleton
    @Named(C_D_DTO_TO_ENTITY)
    internal fun providesCharacterDtoToEntityMapper():
            Mapper<@JvmSuppressWildcards CharacterListQuery.Result, @JvmSuppressWildcards CharacterEntity> =
        CharacterDtoToEntityMapper()

    @Provides
    @Singleton
    @Named(C_D_ENTITY_TO_DOMAIN)
    internal fun providesCharacterEntityToDtoMapper():
            Mapper<@JvmSuppressWildcards CharacterEntity, @JvmSuppressWildcards Character> =
        CharacterEntityToDomainMapper()*/
}