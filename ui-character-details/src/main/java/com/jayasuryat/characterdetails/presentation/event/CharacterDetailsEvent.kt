package com.jayasuryat.characterdetails.presentation.event

import com.jayasuryat.event.Event

sealed interface CharacterDetailsEvent : Event {

    object OnBackClicked : CharacterDetailsEvent

    data class OpenCharacterEpisodes(
        val characterId: Long,
    ) : CharacterDetailsEvent

    data class OpenLocation(
        val locationId: Long,
    ) : CharacterDetailsEvent
}