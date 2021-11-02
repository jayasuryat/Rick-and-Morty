package com.jayasuryat.characterlist.presentation.event

import com.jayasuryat.event.Event

sealed interface CharacterListEvent : Event {

    object OnBackClicked : CharacterListEvent

    data class OpenCharacter(
        val characterId: Long,
    ) : CharacterListEvent
}