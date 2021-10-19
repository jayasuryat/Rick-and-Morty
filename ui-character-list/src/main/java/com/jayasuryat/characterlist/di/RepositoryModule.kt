package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.repositories.CharacterListRepo
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import com.jayasuryat.characterlist.di.MapperModule.C_D_DTO_TO_ENTITY
import com.jayasuryat.characterlist.di.MapperModule.C_D_ENTITY_TO_DOMAIN
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun providesCharacterListRepo(
        dispatcherProvider: DispatcherProvider,
        networkDataSource: CharacterListNetworkDataSource,
        localDataSource: CharacterListLocalDataSource,

        @Named(C_D_DTO_TO_ENTITY)
        characterDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards CharacterListQuery.Result, @JvmSuppressWildcards CharacterEntity>,

        @Named(C_D_ENTITY_TO_DOMAIN)
        characterEntityToDtoMapper:
        Mapper<@JvmSuppressWildcards CharacterEntity, @JvmSuppressWildcards Character>,
    ): CharacterListRepository = CharacterListRepo(
        dispatcher = dispatcherProvider,
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        characterDtoToEntityMapper = characterDtoToEntityMapper,
        characterEntityToDomainMapper = characterEntityToDtoMapper,
    )
}