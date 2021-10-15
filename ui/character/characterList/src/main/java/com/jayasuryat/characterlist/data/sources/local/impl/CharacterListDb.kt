package com.jayasuryat.characterlist.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false,
)
internal abstract class CharacterListDb : RoomDatabase() {

    abstract fun characterListDao(): CharacterListLocalDataSource

    internal companion object {

        const val DB_NAME: String = "CharacterListDb"
    }
}