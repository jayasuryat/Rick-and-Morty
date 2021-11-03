package com.jayasuryat.episodedetails.presentation

import com.jayasuryat.episodedetails.domain.model.Character

internal data class EpisodeData(
    val episodeName: String,
    val episode: String,
    val season: String,
    val episodeAiredOn: String,
    val characters: List<Character>,
)
