package com.jayasuryat.episodelist.presentation.event

import com.jayasuryat.event.Event

sealed interface EpisodeListEvent : Event {

    object OnBackClicked : EpisodeListEvent

    data class OpenEpisode(
        val episodeId: Long,
    ) : EpisodeListEvent

}