package com.jayasuryat.characterdetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
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
            Mapper<CharacterDetailsQuery.Character, CharacterEntity> =
        CharacterDtoToEntityMapper()

    @Provides
    @Singleton
    internal fun providesCharacterEntityToDtoMapper():
            Mapper<CharacterEntity, CharacterDetails> =
        CharacterEntityToDomainMapper()
}