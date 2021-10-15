package com.jayasuryat.characterdetails.domain.repos

import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.characterdetails.domain.models.CharacterDetails

interface CharacterDetailsRepository {

    suspend fun getCharacterDetails(characterId: Long): KResult<CharacterDetails>
    suspend fun getCharacterDetailsFromCache(characterId: Long): KResult<CharacterDetails>
}