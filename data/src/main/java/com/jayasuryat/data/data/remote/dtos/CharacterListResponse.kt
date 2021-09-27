package com.jayasuryat.data.data.remote.dtos

import com.jayasuryat.data.models.DtoModel
import kotlinx.serialization.Serializable


@Serializable
internal data class CharacterListResponse(
    val info: RemoteResultInfo,
    val results: List<CharacterDto>,
)

@Serializable
internal data class CharacterDto(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) : DtoModel

@Serializable
internal data class Location(
    val name: String,
    val url: String,
)