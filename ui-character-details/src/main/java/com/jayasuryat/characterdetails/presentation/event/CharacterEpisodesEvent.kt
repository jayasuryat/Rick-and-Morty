package com.jayasuryat.characterdetails.presentation.event

import com.jayasuryat.event.Event

sealed interface CharacterEpisodesEvent : Event {

    object OnBackClicked : CharacterEpisodesEvent

    data class OpenEpisode(
        val episodeId: Long,
    ) : CharacterEpisodesEvent
}