package com.jayasuryat.locationdetails.domain.repositories

import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.locationdetails.domain.models.LocationDetails

interface LocationDetailsRepository {

    suspend fun getLocationDetails(
        locationId: Long,
    ): KResult<LocationDetails>
}