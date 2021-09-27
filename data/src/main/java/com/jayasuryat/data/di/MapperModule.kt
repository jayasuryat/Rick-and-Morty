package com.jayasuryat.data.di

import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.data.remote.dtos.CharacterDto
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.datasources.mappers.impl.character.CharacterDtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.impl.character.CharacterEntityToDomainMapper
import com.jayasuryat.data.models.domain.Character
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Singleton
    @Provides
    internal fun providesCharacterDtoToEntityMapper():
            DtoToEntityMapper<CharacterDto, CharacterEntity> = CharacterDtoToEntityMapper()

    @Singleton
    @Provides
    internal fun providesCharacterEntityToDomainMapper():
            EntityToDomainMapper<CharacterEntity, Character> = CharacterEntityToDomainMapper()
}