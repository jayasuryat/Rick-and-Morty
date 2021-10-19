package com.jayasuryat.episodedetails.domain.repositories

import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.episodedetails.domain.model.EpisodeDetails

interface EpisodeDetailsRepository {

    suspend fun getEpisodeDetails(episodeId: Long): KResult<EpisodeDetails>
}