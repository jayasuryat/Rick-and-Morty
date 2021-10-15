package com.jayasuryat.characterdetails.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource
import com.jayasuryat.characterdetails.data.sources.remote.impl.CharacterDetailsRemoteDataSourceImpl
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
    fun providesCharacterDetailsNetworkDataSource(
        graphQlClientProvider: GraphQlClientProvider,
    ): CharacterDetailsRemoteDataSource {
        return CharacterDetailsRemoteDataSourceImpl(graphQlClientProvider.getClient())
    }
}