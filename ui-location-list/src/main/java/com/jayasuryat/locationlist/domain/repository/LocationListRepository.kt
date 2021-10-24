package com.jayasuryat.locationlist.domain.repository

import androidx.paging.PagingData
import com.jayasuryat.locationlist.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationListRepository {

    fun getPagedLocations(): Flow<PagingData<Location>>
}