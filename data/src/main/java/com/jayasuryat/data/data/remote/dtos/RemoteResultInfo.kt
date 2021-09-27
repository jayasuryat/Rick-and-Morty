package com.jayasuryat.data.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
internal data class RemoteResultInfo(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: String?,
)