package com.jayasuryat.data.models.domain

import com.jayasuryat.data.models.DomainModel


public data class Episode(
    val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : DomainModel