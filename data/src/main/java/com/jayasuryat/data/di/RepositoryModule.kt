package com.jayasuryat.data.di

import com.jayasuryat.data.data.local.definitions.CharactersLocalDataSource
import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.data.remote.definitions.CharactersRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.CharacterDto
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.datasources.impl.CharactersRepoImpl
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.models.domain.Character
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Singleton
    @Provides
    internal fun providesCharacterRepository(
        networkClient: CharactersRemoteDataSource,
        cacheClient: CharactersLocalDataSource,
        characterDtoToEntity: DtoToEntityMapper<CharacterDto, CharacterEntity>,
        characterEntityToDomain: EntityToDomainMapper<CharacterEntity, Character>
    ): CharactersRepository = CharactersRepoImpl(
        networkClient = networkClient,
        cacheClient = cacheClient,
        characterDtoToEntity = characterDtoToEntity,
        characterEntityToDomain = characterEntityToDomain,
    )
}