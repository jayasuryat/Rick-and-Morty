package com.jayasuryat.locationdetails.data.mappers


import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails


internal class LocationDetailsEntityToDomainMapper :
    Mapper<LocationDetailsEntity, LocationDetails>(mappingStrategy = ::mappingStrategy) {

    private companion object {

        private fun mappingStrategy(
            input: LocationDetailsEntity,
        ): LocationDetails = LocationDetails(
            id = input.id,
            name = input.name,
            type = input.type,
            dimension = input.dimension,
            characters = input.residents.map { resident ->
                Character(
                    id = resident.id,
                    name = resident.name,
                    image = resident.image,
                )
            }
        )
    }
}
