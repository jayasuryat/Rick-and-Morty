package com.jayasuryat.home

import androidx.navigation.Navigator

sealed interface HomeScreenEvent

class OpenCharacters(
    val extras: Navigator.Extras,
) : HomeScreenEvent

class OpenEpisodes(
    val extras: Navigator.Extras,
) : HomeScreenEvent

class OpenLocations(
    val extras: Navigator.Extras,
) : HomeScreenEvent