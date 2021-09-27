package com.jayasuryat.data.data.remote.dtos

import com.jayasuryat.data.models.DtoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class EpisodesListResponse(
    val info: RemoteResultInfo,
    val results: List<EpisodeDto>
)

@Serializable
internal data class EpisodeDto(
    val id: Long,
    val name: String,
    @SerialName("air_date") val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : DtoModel