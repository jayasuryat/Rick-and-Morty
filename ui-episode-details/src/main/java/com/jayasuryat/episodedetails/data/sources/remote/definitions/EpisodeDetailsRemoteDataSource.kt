package com.jayasuryat.episodedetails.data.sources.remote.definitions

import com.apollographql.apollo.api.Response
import com.jayasuryat.episodedetails.EpisodeDetailQuery

internal interface EpisodeDetailsRemoteDataSource {

    suspend fun getEpisodeDetails(
        episodeId: Long,
    ): Response<EpisodeDetailQuery.Data>
}