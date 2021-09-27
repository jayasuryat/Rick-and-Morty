package com.jayasuryat.data.data.remote.dtos

import com.jayasuryat.data.models.DtoModel
import kotlinx.serialization.Serializable


@Serializable
internal data class LocationListResponse(
    val info: RemoteResultInfo,
    val results: List<EpisodeDto>
)

@Serializable
internal data class LocationDto(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
) : DtoModel
