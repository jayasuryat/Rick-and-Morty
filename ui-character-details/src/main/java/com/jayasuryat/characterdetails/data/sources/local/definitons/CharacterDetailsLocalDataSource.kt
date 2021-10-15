package com.jayasuryat.characterdetails.data.sources.local.definitons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity


@Dao
internal interface CharacterDetailsLocalDataSource {

    @Query("SELECT * FROM character_details WHERE id = :characterId")
    suspend fun getCharacter(characterId: Long): CharacterEntity

    @Insert(onConflict = REPLACE)
    suspend fun saveCharacterDetails(characterDetails: CharacterEntity)
}