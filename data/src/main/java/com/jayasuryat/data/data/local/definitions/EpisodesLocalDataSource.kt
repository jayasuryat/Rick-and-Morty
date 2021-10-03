package com.jayasuryat.data.data.local.definitions

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.data.data.local.entities.EpisodeEntity

internal interface EpisodesLocalDataSource : LocalDataSource {

    @Query("SELECT * FROM episode")
    suspend fun getAllEpisodes(): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEpisodes(characters: List<EpisodeEntity>)

    @Query("SELECT * FROM episode WHERE id=:episodeId")
    suspend fun getEpisodeForId(episodeId: Long): EpisodeEntity

    @Query("SELECT * FROM episode WHERE episode.id IN (:episodeIds)")
    suspend fun getEpisodesFor(episodeIds: List<Long>): List<EpisodeEntity>
}