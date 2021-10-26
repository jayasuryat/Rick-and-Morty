package com.jayasuryat.locationdetails.presentation

import androidx.navigation.Navigator

sealed interface LocationDetailsEvent

object NavigateBack : LocationDetailsEvent

class OpenCharacter(
    val characterId: Long,
    val extras: Navigator.Extras,
) : LocationDetailsEvent