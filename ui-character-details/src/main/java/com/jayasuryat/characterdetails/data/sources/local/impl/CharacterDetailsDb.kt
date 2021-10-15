package com.jayasuryat.characterdetails.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity


@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    CharacterEntity.TypeConvert::class,
)
internal abstract class CharacterDetailsDb : RoomDatabase() {

    abstract fun getCharacterDetailsDao(): CharacterDetailsLocalDataSource

    internal companion object {

        internal const val DB_NAME: String = "CharacterDetailsDb"
    }
}