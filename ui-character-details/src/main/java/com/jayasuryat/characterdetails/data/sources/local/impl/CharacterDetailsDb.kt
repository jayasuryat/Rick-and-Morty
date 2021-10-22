package com.jayasuryat.characterdetails.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEpisodesEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity


@Database(
    entities = [
        CharacterEntity::class,
        EpisodeEntity::class,
        CharacterEpisodesEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    CharacterEpisodesEntity.TypeConverter::class,
)
internal abstract class CharacterDetailsDb : RoomDatabase() {

    abstract fun getCharacterDetailsDao(): CharacterDetailsLocalDataSource

    internal companion object {

        internal const val DB_NAME: String = "CharacterDetailsDb"
    }
}