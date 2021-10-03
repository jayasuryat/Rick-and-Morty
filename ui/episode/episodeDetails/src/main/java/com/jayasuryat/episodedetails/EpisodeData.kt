package com.jayasuryat.episodedetails

import com.jayasuryat.data.models.domain.Episode

internal data class EpisodeData(
    val episodeData: Episode,
    val season: String,
    val episode: String,
)
