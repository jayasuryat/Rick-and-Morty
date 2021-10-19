package com.jayasuryat.episodelist.domain.repos


import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.episodelist.domain.models.Episode


interface EpisodeListRepository {

    suspend fun getAllEpisodes(): KResult<List<Episode>>

    suspend fun getAllEpisodesInCache(): KResult<List<Episode>>
}