package com.jayasuryat.home.event

import com.jayasuryat.base.event.Event

sealed interface HomeEvent : Event {

    object OpenCharacters : HomeEvent
    object OpenEpisodes : HomeEvent
    object OpenLocations : HomeEvent
}