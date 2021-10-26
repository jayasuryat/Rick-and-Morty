package com.jayasuryat.locationdetails.data.repos

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.locationdetails.LocationDetailsQuery
import com.jayasuryat.locationdetails.data.sources.local.definitions.LocationDetailsLocalDataSource
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity
import com.jayasuryat.locationdetails.data.sources.remote.definitions.LocationDetailsRemoteDataSource
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.locationdetails.domain.repositories.LocationDetailsRepository

internal class LocationDetailsRepo(
    private val dispatcher: DispatcherProvider,
    private val cacheClient: LocationDetailsLocalDataSource,
    private val remoteClient: LocationDetailsRemoteDataSource,
    private val locationEntityToDomainMapper: Mapper<LocationDetailsEntity, LocationDetails>,
    private val locationDtoToEntityMapper: Mapper<LocationDetailsQuery.Location, LocationDetailsEntity>,
) : LocationDetailsRepository {

    override suspend fun getLocationDetails(
        locationId: Long,
    ): KResult<LocationDetails> = wrapAsResult(dispatcher.io()) {

        cacheClient.getLocationDetails(locationId)?.let { cachedLocation ->
            return@wrapAsResult locationEntityToDomainMapper.map(cachedLocation)
        }

        val remoteLocation = remoteClient.getLocationDetails(locationId).data?.location
            ?: throw RuntimeException("Remote location details with id $locationId not found")

        cacheClient.saveLocationDetails(locationDtoToEntityMapper.map(remoteLocation))

        val cachedLocation = cacheClient.getLocationDetails(locationId)
            ?: throw RuntimeException("Cache location details with id $locationId not found")

        locationEntityToDomainMapper.map(cachedLocation)
    }
}