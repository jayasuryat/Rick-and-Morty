package com.jayasuryat.episodelist.domain.models

import com.jayasuryat.episodelist.EpisodeListsDomainModel


data class Episode(
    val id: Long,
    val episode: String,
    val name: String,
) : EpisodeListsDomainModel
