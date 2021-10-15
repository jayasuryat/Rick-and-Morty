package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterlist.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterlist.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterlist.data.repositories.CharacterListRepo
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        /*characterDtoToEntityMapper: Mapper<CharacterListQuery.Result, CharacterEntity>,
        characterEntityToDtoMapper: Mapper<CharacterEntity, Character>,*/
    ): CharacterListRepository = CharacterListRepo(
        dispatcher = dispatcherProvider,
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        characterDtoToEntityMapper = CharacterDtoToEntityMapper(),
        characterEntityToDomainMapper = CharacterEntityToDomainMapper(),
    )
}