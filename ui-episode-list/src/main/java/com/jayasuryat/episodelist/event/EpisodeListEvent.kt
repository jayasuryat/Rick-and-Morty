package com.jayasuryat.episodelist.event

import com.jayasuryat.event.Event

sealed interface EpisodeListEvent : Event {

    object OnBackClicked : EpisodeListEvent

    data class OpenEpisode(
        val episodeId: Long,
    ) : EpisodeListEvent

}