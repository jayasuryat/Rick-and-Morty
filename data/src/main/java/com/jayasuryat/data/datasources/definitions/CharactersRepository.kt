package com.jayasuryat.data.datasources.definitions

import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Character

public interface CharactersRepository {

    public suspend fun getCharacters(page: Int): KResult<List<Character>>

    public suspend fun getAllCharactersInCache(): KResult<List<Character>>

    public suspend fun getCharacterFromCache(characterId: Long): KResult<Character>
}