package com.jayasuryat.locationdetails.data.sources.remote.definitions

import com.apollographql.apollo.api.Response
import com.jayasuryat.locationdetails.LocationDetailsQuery

internal interface LocationDetailsRemoteDataSource {

    suspend fun getLocationDetails(
        locationId: Long,
    ): Response<LocationDetailsQuery.Data>
}