package com.jayasuryat.episodedetails.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.episodedetails.data.sources.remote.definitions.EpisodeDetailsRemoteDataSource
import com.jayasuryat.episodedetails.data.sources.remote.impl.EpisodeDetailsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesCharacterListNetworkDataSource(
        graphQlClientProvider: GraphQlClientProvider,
    ): EpisodeDetailsRemoteDataSource {
        return EpisodeDetailsRemoteDataSourceImpl(graphQlClientProvider.getClient())
    }
}