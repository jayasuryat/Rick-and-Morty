package com.jayasuryat.characterdetails.data.sources.remote.definitons

import com.apollographql.apollo.api.Response
import com.jayasuryat.characterdetails.CharacterDetailsQuery

internal interface CharacterDetailsRemoteDataSource {

    suspend fun getCharacterDetails(characterId: Long): Response<CharacterDetailsQuery.Data>
}