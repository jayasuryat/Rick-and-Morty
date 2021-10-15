package com.jayasuryat.characterlist


internal sealed interface CharacterListModel {

    fun getDummy(): String = javaClass.simpleName
}

internal interface CharacterListDto : CharacterListModel
internal interface CharacterListEntity : CharacterListModel
internal interface CharacterListDomainModel : CharacterListModel
