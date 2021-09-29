package com.jayasuryat.home

import android.graphics.Point
import androidx.navigation.Navigator

sealed interface HomeScreenEvent

class OpenCharacters(
    val extras: Navigator.Extras,
    val clickPoint: Point,
) : HomeScreenEvent

object OpenEpisodes : HomeScreenEvent
object OpenLocations : HomeScreenEvent