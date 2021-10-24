package com.jayasuryat.episodedetails.domain.model

import com.jayasuryat.episodedetails.EpisodeDetailsDomainModel


data class EpisodeDetails(
    val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Character>,
) : EpisodeDetailsDomainModel

data class Character(
    val id: Long,
    val name: String,
    val image: String,
)
