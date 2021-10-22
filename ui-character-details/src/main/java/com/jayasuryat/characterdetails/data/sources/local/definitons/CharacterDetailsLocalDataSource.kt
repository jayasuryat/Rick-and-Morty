package com.jayasuryat.characterdetails.data.sources.local.definitons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEpisodesEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity


@Dao
internal interface CharacterDetailsLocalDataSource {

    @Query("SELECT * FROM character_details WHERE id=:characterId")
    suspend fun getCharacter(characterId: Long): CharacterEntity?

    @Insert(onConflict = REPLACE)
    suspend fun saveCharacterDetails(characterDetails: CharacterEntity)


    @Query("SELECT * FROM episode WHERE id IN (:ids)")
    suspend fun getEpisodes(ids: List<Long>): List<EpisodeEntity>?

    @Insert(onConflict = REPLACE)
    suspend fun saveEpisodes(episodeEntity: List<EpisodeEntity>)


    @Query("SELECT * FROM character_episode WHERE characterId = :characterId")
    suspend fun getCharacterEpisodes(characterId: Long): CharacterEpisodesEntity?

    @Insert(onConflict = REPLACE)
    suspend fun saveCharacterEpisodes(characterEpisodes: CharacterEpisodesEntity)
}