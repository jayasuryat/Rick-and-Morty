package com.jayasuryat.data.data.remote.definitions

import com.jayasuryat.data.data.remote.dtos.CharacterDto
import com.jayasuryat.data.data.remote.dtos.CharacterListResponse

internal interface CharactersRemoteDataSource : RemoteDataSource {

    suspend fun getCharacters(page: Int): CharacterListResponse

    suspend fun getCharactersById(ids: List<Long>): List<CharacterDto>
}