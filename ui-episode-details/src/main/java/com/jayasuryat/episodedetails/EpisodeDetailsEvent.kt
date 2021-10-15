package com.jayasuryat.episodedetails

import androidx.navigation.Navigator

sealed interface EpisodeDetailsEvent

object NavigateBack : EpisodeDetailsEvent

class OpenCharacter(
    val characterId: Long,
    val extras: Navigator.Extras,
) : EpisodeDetailsEvent