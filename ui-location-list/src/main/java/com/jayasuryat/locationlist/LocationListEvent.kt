package com.jayasuryat.locationlist

import androidx.navigation.Navigator

sealed interface LocationListEvent

class OpenLocation(
    val locationId: Long,
    val extras: Navigator.Extras,
) : LocationListEvent

object NavigateBack : LocationListEvent