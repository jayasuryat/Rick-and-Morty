package com.jayasuryat.episodelist.data.sources.local.definitions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity


@Dao
internal interface EpisodeListLocalDataSource {

    @Query("SELECT * FROM episode_def")
    suspend fun getAllEpisodes(): List<EpisodeEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEpisodes(episodes: List<EpisodeEntity>)

}