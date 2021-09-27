package com.jayasuryat.data.datasources.definitions

import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Location

public interface LocationsRepository {

    public fun getLocations(page: Int): KResult<List<Location>>
}