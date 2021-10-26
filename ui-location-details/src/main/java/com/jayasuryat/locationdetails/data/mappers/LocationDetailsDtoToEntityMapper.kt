package com.jayasuryat.locationdetails.data.mappers


import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationdetails.LocationDetailsQuery
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity
import com.jayasuryat.locationdetails.data.sources.local.entities.Resident

internal class LocationDetailsDtoToEntityMapper :
    Mapper<LocationDetailsQuery.Location, LocationDetailsEntity>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: LocationDetailsQuery.Location,
        ): LocationDetailsEntity = LocationDetailsEntity(
            id = input.id!!.toLong(),
            name = input.name!!,
            type = input.type!!,
            dimension = input.dimension!!,
            residents = input.residents.map { resident ->
                Resident(
                    id = resident!!.id!!.toLong(),
                    name = resident.name!!,
                    image = resident.image!!,
                )
            }
        )
    }
}
