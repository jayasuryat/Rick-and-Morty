package com.jayasuryat.characterlist.di

import com.apollographql.apollo.ApolloClient
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
        apolloClient: ApolloClient
    ): CharacterListNetworkDataSource {
        return CharacterListNetworkDataSourceImpl(apolloClient)
    }
}