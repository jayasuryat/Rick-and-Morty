package com.jayasuryat.locationlist.di

import com.jayasuryat.basedata.providers.GraphQlClientProvider
import com.jayasuryat.locationlist.data.sources.remote.definitions.LocationListRemoteDataSource
import com.jayasuryat.locationlist.data.sources.remote.impl.LocationListRemoteDataSourceImpl
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
    fun providesLocationListNetworkDataSource(
        graphQlClientProvider: GraphQlClientProvider,
    ): LocationListRemoteDataSource {
        return LocationListRemoteDataSourceImpl(graphQlClientProvider.getClient())
    }
}