package com.jayasuryat.characterdetails.di

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.CharacterEpisodeQuery
import com.jayasuryat.characterdetails.data.mappers.CharacterDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEntityToDomainMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeDtoToEntityMapper
import com.jayasuryat.characterdetails.data.mappers.CharacterEpisodeEntityToDomainMapper
import com.jayasuryat.characterdetails.data.repos.CharacterDetailsRepo
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource
import com.jayasuryat.characterdetails.di.MapperModule.C_D_DTO_TO_ENTITY
import com.jayasuryat.characterdetails.di.MapperModule.C_D_ENTITY_TO_DOMAIN
import com.jayasuryat.characterdetails.di.MapperModule.C_E_DTO_TO_ENTITY
import com.jayasuryat.characterdetails.di.MapperModule.C_E_ENTITY_TO_DOMAIN
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Episode
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    internal fun providesCharacterDetailsRepo(
        dispatcherProvider: DispatcherProvider,
        networkDataSource: CharacterDetailsRemoteDataSource,
        localDataSource: CharacterDetailsLocalDataSource,

      /*  @Named(C_D_DTO_TO_ENTITY)
        characterDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards CharacterDetailsQuery.Character,
                @JvmSuppressWildcards CharacterEntity>,

        @Named(C_D_ENTITY_TO_DOMAIN)
        characterEntityToDomainMapper:
        Mapper<@JvmSuppressWildcards CharacterEntity,
                @JvmSuppressWildcards CharacterDetails>,

        @Named(C_E_DTO_TO_ENTITY)
        characterEpisodeDtoToEntityMapper:
        Mapper<@JvmSuppressWildcards CharacterEpisodeQuery.Episode,
                @JvmSuppressWildcards EpisodeEntity>,

        @Named(C_E_ENTITY_TO_DOMAIN)
        characterEpisodeEntityToDomainMapper:
        Mapper<@JvmSuppressWildcards EpisodeEntity,
                @JvmSuppressWildcards Episode>,*/

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