package com.jayasuryat.episodedetails.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayasuryat.episodedetails.data.sources.local.definitions.EpisodeDetailsLocalDataSource
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity

@Database(
    entities = [EpisodeDetailsEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    EpisodeDetailsEntity.Converter::class,
)
internal abstract class EpisodeDetailsDb : RoomDatabase() {

    abstract fun episodeDetailsDao(): EpisodeDetailsLocalDataSource

    internal companion object {

        const val DB_NAME: String = "EpisodeDetailsDb"
    }
}