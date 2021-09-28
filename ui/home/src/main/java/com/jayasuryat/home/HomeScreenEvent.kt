package com.jayasuryat.home

sealed interface HomeScreenEvent

object OpenCharacters : HomeScreenEvent
object OpenEpisodes : HomeScreenEvent
object OpenLocations : HomeScreenEvent