package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.repositories.CharacterListRemoteMediator
import com.jayasuryat.characterlist.data.repositories.CharacterListRepo
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import com.jayasuryat.characterlist.di.MapperModule.C_D_DTO_TO_ENTITY
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
    fun providesRemoteMediator(
        networkDataSource: CharacterListNetworkDataSource,
        localDataSource: CharacterListLocalDataSource,
        @Named(C_D_DTO_TO_ENTITY)
        characterDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards CharacterListQuery.Result, @JvmSuppressWildcards CharacterEntity>,
    ): CharacterListRemoteMediator = CharacterListRemoteMediator(
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        characterDtoToEntityMapper = characterDtoToEntityMapper
    )

    @Provides
    @Singleton
    fun test(
        remoteMediator: CharacterListRemoteMediator,
        localDataSource: CharacterListLocalDataSource,
        @Named(MapperModule.C_D_ENTITY_TO_DOMAIN)
        characterEntityToDomainMapper:
        Mapper<@JvmSuppressWildcards CharacterEntity, @JvmSuppressWildcards Character>,
    ): CharacterListRepository = CharacterListRepo(
        mediator = remoteMediator,
        cacheClient = localDataSource,
        characterEntityToDomainMapper = characterEntityToDomainMapper,
    )
}