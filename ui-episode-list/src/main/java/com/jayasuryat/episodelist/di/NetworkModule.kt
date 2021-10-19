package com.jayasuryat.episodelist.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.episodelist.data.sources.remote.definitions.EpisodeListRemoteDataSource
import com.jayasuryat.episodelist.data.sources.remote.impls.EpisodeListRemoteDataSourceImpl
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
    ): EpisodeListRemoteDataSource {
        return EpisodeListRemoteDataSourceImpl(graphQlClientProvider.getClient())
    }
}