package com.jayasuryat.episodelist.data.sources.local.impls

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jayasuryat.episodelist.data.sources.local.definitions.EpisodeListLocalDataSource
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity

@Database(
    entities = [EpisodeEntity::class],
    version = 1,
    exportSchema = false,
)
internal abstract class EpisodeListDb : RoomDatabase() {

    abstract fun episodeListDao(): EpisodeListLocalDataSource

    internal companion object {

        const val DB_NAME: String = "EpisodeListDb"
    }
}