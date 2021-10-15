package com.jayasuryat.characterlist.domain.repos.definitions

import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.characterlist.domain.models.Character

interface CharacterListRepository {

    suspend fun getCharacters(
        page: Int,
    ): KResult<List<Character>>

    suspend fun getAllCharactersInCache(): KResult<List<Character>>
}