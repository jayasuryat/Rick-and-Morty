package com.jayasuryat.episodedetails.data.sources.remote.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.episodedetails.EpisodeDetailQuery
import com.jayasuryat.episodedetails.data.sources.remote.definitions.EpisodeDetailsRemoteDataSource

internal class EpisodeDetailsRemoteDataSourceImpl(
    private val apolloClient: ApolloClient,
) : EpisodeDetailsRemoteDataSource {

    override suspend fun getEpisodeDetails(
        episodeId: Long,
    ): Response<EpisodeDetailQuery.Data> {
        return apolloClient.query(EpisodeDetailQuery(episodeId.toString())).await()
    }
}