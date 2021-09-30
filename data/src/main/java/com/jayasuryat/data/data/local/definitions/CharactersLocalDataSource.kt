package com.jayasuryat.data.data.local.definitions

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.data.data.local.entities.CharacterEntity

internal interface CharactersLocalDataSource : LocalDataSource {

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character WHERE id=:characterId")
    suspend fun getCharacterById(characterId: Long): CharacterEntity
}