package com.jayasuryat.locationlist.data.repos

import androidx.paging.*
import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.locationlist.domain.repository.LocationListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
internal class LocationListRepo constructor(
    private val mediator: RemoteMediator<Int, LocationEntity>,
    private val cacheClient: LocationListLocalDataSource,
    private val locationEntityToDomainMapper: Mapper<LocationEntity, Location>,
) : LocationListRepository {

    override fun getPagedLocations(): Flow<PagingData<Location>> {

        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
        )

        return Pager(
            config = pagingConfig,
            remoteMediator = mediator,
        ) {
            cacheClient.getPagedLocations()
        }.flow.map {
            it.map(locationEntityToDomainMapper::map)
        }
    }

    private companion object {

        const val PAGE_SIZE: Int = 20
    }
}