package com.jayasuryat.data.data.remote.definitions

import com.jayasuryat.data.data.remote.dtos.EpisodeDto
import com.jayasuryat.data.data.remote.dtos.EpisodesListResponse

internal interface EpisodesRemoteDataSource : RemoteDataSource {

    suspend fun getEpisodes(page: Int): EpisodesListResponse

    suspend fun getEpisodesFor(episodeNumbers: List<Long>): List<EpisodeDto>
}