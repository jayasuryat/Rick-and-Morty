package com.jayasuryat.locationlist.data.sources.remote.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.locationlist.LocationListQuery
import com.jayasuryat.locationlist.data.sources.remote.definitions.LocationListRemoteDataSource


internal class LocationListRemoteDataSourceImpl(
    private val networkClient: ApolloClient,
) : LocationListRemoteDataSource {

    override suspend fun getLocations(page: Int): Response<LocationListQuery.Data> {
        return networkClient.query(LocationListQuery(Input.fromNullable(page)))
            .await()
    }
}
