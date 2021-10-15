package com.jayasuryat.characterlist.data.sources.local.definitions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity


@Dao
internal interface CharacterListLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters LIMIT :limit OFFSET :offset")
    suspend fun getCharacters(limit: Int, offset: Int): List<CharacterEntity>

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>
}