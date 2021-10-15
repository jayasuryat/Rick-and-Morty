package com.jayasuryat.characterlist.data.sources.remote.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import kotlinx.coroutines.withContext


internal class CharacterListNetworkDataSourceImpl(
    private val networkClient: ApolloClient,
) : CharacterListNetworkDataSource {

    override suspend fun getCharacters(page: Int): Response<CharacterListQuery.Data> {
        return networkClient.query(CharacterListQuery(Input.fromNullable(page)))
            .await()
    }
}