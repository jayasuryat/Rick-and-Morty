package com.jayasuryat.episodedetails.presentation

import com.jayasuryat.episodedetails.domain.model.EpisodeDetails

internal data class EpisodeData(
    val episodeData: EpisodeDetails,
    val season: String,
    val episode: String,
)
