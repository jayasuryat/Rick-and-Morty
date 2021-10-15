package com.jayasuryat.characterlist.domain.models

import com.jayasuryat.characterlist.CharacterListDomainModel


data class Character(
    val id: Long,
    val name: String,
    val imageUrl: String,
) : CharacterListDomainModel