package com.jayasuryat.characterdetails.data.sources.remote.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource


internal class CharacterDetailsRemoteDataSourceImpl(
    private val apolloClient: ApolloClient,
) : CharacterDetailsRemoteDataSource {

    override suspend fun getCharacterDetails(
        characterId: Long,
    ): Response<CharacterDetailsQuery.Data> {
        return apolloClient.query(CharacterDetailsQuery(characterId.toString()))
            .await()
    }
}