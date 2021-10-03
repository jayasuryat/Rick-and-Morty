package com.jayasuryat.episodelist

import androidx.navigation.Navigator

sealed interface EpisodeListEvent

class OpenEpisode(
    val episodeId: Long,
    val extras: Navigator.Extras,
) : EpisodeListEvent

object NavigateBack : EpisodeListEvent