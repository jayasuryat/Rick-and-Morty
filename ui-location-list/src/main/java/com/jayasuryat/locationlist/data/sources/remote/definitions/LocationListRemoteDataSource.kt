package com.jayasuryat.locationlist.data.sources.remote.definitions

import com.apollographql.apollo.api.Response
import com.jayasuryat.locationlist.LocationListQuery


internal interface LocationListRemoteDataSource {

    suspend fun getLocations(page: Int): Response<LocationListQuery.Data>
}
