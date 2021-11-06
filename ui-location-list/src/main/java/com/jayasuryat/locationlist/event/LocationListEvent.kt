package com.jayasuryat.locationlist.event

import com.jayasuryat.event.Event

sealed interface LocationListEvent : Event {

    object OnBackClicked : LocationListEvent

    data class OpenLocation(
        val locationId: Long,
    ) : LocationListEvent
}