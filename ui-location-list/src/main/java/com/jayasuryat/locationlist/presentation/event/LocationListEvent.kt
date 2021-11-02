package com.jayasuryat.locationlist.presentation.event

import com.jayasuryat.event.Event
import com.jayasuryat.locationlist.domain.model.Location

sealed interface LocationListEvent : Event {

    object OnBackClicked : LocationListEvent

    data class OpenLocation(val location: Location) : LocationListEvent
}