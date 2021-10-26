package com.jayasuryat.locationdetails.domain.models

import com.jayasuryat.locationdetails.LocationDetailsDomainModel


data class LocationDetails(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val characters: List<Character>,
) : LocationDetailsDomainModel

data class Character(
    val id: Long,
    val name: String,
    val image: String,
)
