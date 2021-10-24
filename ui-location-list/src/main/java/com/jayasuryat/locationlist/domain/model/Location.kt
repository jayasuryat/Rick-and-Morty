package com.jayasuryat.locationlist.domain.model

import com.jayasuryat.locationlist.LocationListDomainModel


data class Location(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
) : LocationListDomainModel
