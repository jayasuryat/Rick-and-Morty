package com.jayasuryat.data.data.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.data.local.entities.EpisodeEntity
import com.jayasuryat.data.data.local.entities.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    CharacterEntity.Converter::class,
)
internal abstract class CacheClient : RoomDatabase() {

    abstract fun cacheDao(): CacheDao

    companion object {

        internal const val DB_NAME: String = "RickAndMortyCache"
    }
}