package com.jayasuryat.episodedetails.domain.model


data class EpisodeDetails(
    val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Character>,
)

data class Character(
    val id: Long,
    val name: String,
    val image: String,
)
