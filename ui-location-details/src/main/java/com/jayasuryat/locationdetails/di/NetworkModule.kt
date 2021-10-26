package com.jayasuryat.locationdetails.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.locationdetails.data.sources.remote.definitions.LocationDetailsRemoteDataSource
import com.jayasuryat.locationdetails.data.sources.remote.impl.LocationDetailsRemoteDataSourceImpl
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
    fun providesLocationNetworkDataSource(
        graphQlClientProvider: GraphQlClientProvider,
    ): LocationDetailsRemoteDataSource {
        return LocationDetailsRemoteDataSourceImpl(graphQlClientProvider.getClient())
    }
}