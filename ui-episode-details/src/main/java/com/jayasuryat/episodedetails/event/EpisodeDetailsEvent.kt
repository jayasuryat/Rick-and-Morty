package com.jayasuryat.episodedetails.event

import com.jayasuryat.event.Event

sealed interface EpisodeDetailsEvent : Event {

    object OnBackClicked : EpisodeDetailsEvent

    data class OpenCharacter(val characterId: Long) : EpisodeDetailsEvent
}