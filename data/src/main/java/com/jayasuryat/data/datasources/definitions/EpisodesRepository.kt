package com.jayasuryat.data.datasources.definitions

import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Episode

public interface EpisodesRepository {

    public suspend fun getEpisodes(page: Int): KResult<List<Episode>>

    public suspend fun getEpisodeFromCache(episodeId: Long): KResult<Episode>

    public suspend fun getAllEpisodes(): KResult<List<Episode>>

    public suspend fun getAllEpisodesInCache(): KResult<List<Episode>>

    public suspend fun getEpisodes(episodes: List<Long>): KResult<List<Episode>>
}