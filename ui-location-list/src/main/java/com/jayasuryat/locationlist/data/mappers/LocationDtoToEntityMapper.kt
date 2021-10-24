package com.jayasuryat.locationlist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationlist.LocationListQuery
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity

internal class LocationDtoToEntityMapper :
    Mapper<LocationListQuery.Result, LocationEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: LocationListQuery.Result,
        ): LocationEntity = LocationEntity(
            id = input.id!!.toLong(),
            name = input.name!!,
            type = input.type!!,
            dimension = input.dimension!!,
        )
    }
}