package com.jayasuryat.characterlist.data.sources.remote.definitions

import com.apollographql.apollo.api.Response
import com.jayasuryat.characterlist.CharacterListQuery


internal interface CharacterListNetworkDataSource {

    suspend fun getCharacters(page: Int): Response<CharacterListQuery.Data>
}