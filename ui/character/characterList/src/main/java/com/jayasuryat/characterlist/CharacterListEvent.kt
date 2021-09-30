package com.jayasuryat.characterlist

import androidx.navigation.Navigator

sealed interface CharacterListEvent

class OpenCharacter(
    val characterId: Long,
    val extras: Navigator.Extras,
) : CharacterListEvent

object NavigateBack : CharacterListEvent