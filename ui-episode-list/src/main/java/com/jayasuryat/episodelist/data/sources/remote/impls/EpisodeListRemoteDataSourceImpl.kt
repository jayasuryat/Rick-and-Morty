package com.jayasuryat.episodelist.data.sources.remote.impls

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.jayasuryat.episodelist.EpisodeCountQuery
import com.jayasuryat.episodelist.EpisodeListQuery
import com.jayasuryat.episodelist.data.sources.remote.definitions.EpisodeListRemoteDataSource


internal class EpisodeListRemoteDataSourceImpl(
    private val apolloClient: ApolloClient,
) : EpisodeListRemoteDataSource {

    override suspend fun getTotalEpisodesCount(): Response<EpisodeCountQuery.Data> {
        return apolloClient.query(EpisodeCountQuery()).await()
    }

    override suspend fun getEpisodes(episodeIds: List<Long>): Response<EpisodeListQuery.Data> {
        val ids = episodeIds.map { it.toString() }
        return apolloClient.query(EpisodeListQuery(ids)).await()
    }
}