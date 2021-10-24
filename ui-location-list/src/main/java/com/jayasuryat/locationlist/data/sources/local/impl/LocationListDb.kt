package com.jayasuryat.locationlist.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity

@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false,
)
internal abstract class LocationListDb : RoomDatabase() {

    abstract fun locationListDao(): LocationListLocalDataSource

    internal companion object {

        const val DB_NAME: String = "LocationListDb"
    }
}