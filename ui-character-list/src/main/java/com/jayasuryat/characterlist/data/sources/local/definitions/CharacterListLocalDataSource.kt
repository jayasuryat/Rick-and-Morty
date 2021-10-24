package com.jayasuryat.characterlist.data.sources.local.definitions

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity


@Dao
internal interface CharacterListLocalDataSource {

    @Query("SELECT * FROM characters")
    fun getPagedCharacters(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}