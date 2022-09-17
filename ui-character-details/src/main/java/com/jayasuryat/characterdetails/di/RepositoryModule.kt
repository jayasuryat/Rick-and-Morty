package com.jayasuryat.characterdetails.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterdetails.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeEntityToDomainMapper
import com.jayasuryat.characterdetails.data.repos.CharacterDetailsRepo
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    internal fun providesCharacterDetailsRepo(
        dispatcherProvider: DispatcherProvider,
        networkDataSource: CharacterDetailsRemoteDataSource,
        localDataSource: CharacterDetailsLocalDataSource,
    ): CharacterDetailsRepository = CharacterDetailsRepo(
        dispatcher = dispatcherProvider,
        networkClient = networkDataSource,
        cacheClient = localDataSource,
        characterDtoToEntityMapper = CharacterDtoToEntityMapper(),
        characterEntityToDomainMapper = CharacterEntityToDomainMapper(),
        episodeDtoToEntityMapper = CharacterEpisodeDtoToEntityMapper(),
        episodeEntityToDomainMapper = CharacterEpisodeEntityToDomainMapper(),
    )
}