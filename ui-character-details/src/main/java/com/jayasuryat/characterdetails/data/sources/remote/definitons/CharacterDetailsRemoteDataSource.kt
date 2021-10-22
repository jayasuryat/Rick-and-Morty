package com.jayasuryat.characterdetails.data.sources.remote.definitons

import com.apollographql.apollo.api.Response
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.CharacterEpisodeQuery

internal interface CharacterDetailsRemoteDataSource {

    suspend fun getCharacterDetails(
        characterId: Long,
    ): Response<CharacterDetailsQuery.Data>

    suspend fun getCharacterEpisodes(
        characterId: Long,
    ): Response<CharacterEpisodeQuery.Data>
}