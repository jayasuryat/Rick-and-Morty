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
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Provides
    @Singleton
    internal fun providesCharacterDtoToEntityMapper():
            Mapper<CharacterListQuery.Result, CharacterEntity> =
        CharacterDtoToEntityMapper()

    @Provides
    @Singleton
    internal fun providesCharacterEntityToDtoMapper():
            Mapper<CharacterEntity, Character> =
        CharacterEntityToDomainMapper()
}