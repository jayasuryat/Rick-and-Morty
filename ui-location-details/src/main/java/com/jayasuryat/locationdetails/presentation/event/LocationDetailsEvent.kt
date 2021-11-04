package com.jayasuryat.locationdetails.presentation.event

import com.jayasuryat.event.Event

sealed interface LocationDetailsEvent : Event {

    object OnBackClicked : LocationDetailsEvent

    data class OpenCharacter(
        val characterId: Long,
    ) : LocationDetailsEvent
}