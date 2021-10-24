package com.jayasuryat.locationlist.data.mappers

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity
import com.jayasuryat.locationlist.domain.model.Location

internal class LocationEntityToDomainMapper :
    Mapper<LocationEntity, Location>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: LocationEntity,
        ): Location = Location(
            id = input.id,
            name = input.name,
            type = input.type,
            dimension = input.dimension,
        )
    }
}