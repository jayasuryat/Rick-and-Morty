package com.jayasuryat.characterlist


internal sealed interface CharacterListModel

internal interface CharacterListDto : CharacterListModel
internal interface CharacterListEntity : CharacterListModel
internal interface CharacterListDomainModel : CharacterListModel
