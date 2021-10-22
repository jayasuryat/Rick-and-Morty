package com.jayasuryat.characterdetails.domain.repos

import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Episode

interface CharacterDetailsRepository {

    suspend fun getCharacterDetails(characterId: Long): KResult<CharacterDetails>

    suspend fun getCharacterEpisodes(characterId: Long): KResult<List<Episode>>
}