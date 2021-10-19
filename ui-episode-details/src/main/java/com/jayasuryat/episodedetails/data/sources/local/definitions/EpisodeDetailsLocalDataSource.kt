package com.jayasuryat.episodedetails.data.sources.local.definitions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity


@Dao
internal interface EpisodeDetailsLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEpisodeDetails(episodeDetails: EpisodeDetailsEntity)

    @Query("SELECT * FROM episode_details WHERE id=:episodeId")
    fun getEpisodeDetails(episodeId: Long): EpisodeDetailsEntity?
}