package com.jayasuryat.data.datasources.definitions

import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Episode

public interface EpisodesRepository {

    public fun getEpisodes(page: Int): KResult<List<Episode>>
}