package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import com.jayasuryat.characterlist.data.sources.remote.impl.CharacterListNetworkDataSourceImpl
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
    ): CharacterListNetworkDataSource {
        return CharacterListNetworkDataSourceImpl(graphQlClientProvider.getClient())
    }
}