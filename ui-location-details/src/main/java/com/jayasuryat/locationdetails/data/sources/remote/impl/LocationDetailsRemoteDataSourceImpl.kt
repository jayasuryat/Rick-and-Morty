package com.jayasuryat.locationdetails.data.sources.remote.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.locationdetails.LocationDetailsQuery
import com.jayasuryat.locationdetails.data.sources.remote.definitions.LocationDetailsRemoteDataSource

internal class LocationDetailsRemoteDataSourceImpl(
    private val apolloClient: ApolloClient,
) : LocationDetailsRemoteDataSource {

    override suspend fun getLocationDetails(
        locationId: Long,
    ): Response<LocationDetailsQuery.Data> {
        return apolloClient.query(LocationDetailsQuery(locationId.toString())).await()
    }
}