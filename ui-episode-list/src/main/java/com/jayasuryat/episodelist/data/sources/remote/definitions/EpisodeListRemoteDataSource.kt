package com.jayasuryat.episodelist.data.sources.remote.definitions

import com.apollographql.apollo.api.Response
import com.jayasuryat.episodelist.EpisodeCountQuery
import com.jayasuryat.episodelist.EpisodeListQuery

internal interface EpisodeListRemoteDataSource {

    suspend fun getTotalEpisodesCount(): Response<EpisodeCountQuery.Data>

    suspend fun getEpisodes(episodeIds: List<Long>): Response<EpisodeListQuery.Data>
}