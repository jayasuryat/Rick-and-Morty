package com.jayasuryat.data.data.remote.definitions

import com.jayasuryat.data.data.remote.dtos.LocationListResponse

internal interface LocationsRemoteDataSource {

    suspend fun getLocations(page: Int): LocationListResponse
}