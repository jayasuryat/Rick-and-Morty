package com.jayasuryat.characterdetails.presentation.event

import com.jayasuryat.event.Event

sealed interface CharacterDetailsEvent : Event {

    object OnBackPressed : CharacterDetailsEvent

    object OpenCharacterEpisodes : CharacterDetailsEvent

    data class OpenLocation(
        val locationId: Long,
    ) : CharacterDetailsEvent
}