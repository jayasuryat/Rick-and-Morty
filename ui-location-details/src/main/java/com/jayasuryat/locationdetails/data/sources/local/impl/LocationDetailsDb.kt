package com.jayasuryat.locationdetails.data.sources.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jayasuryat.locationdetails.data.sources.local.definitions.LocationDetailsLocalDataSource
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity

@Database(
    entities = [LocationDetailsEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    LocationDetailsEntity.TypeConverter::class,
)
internal abstract class LocationDetailsDb : RoomDatabase() {

    abstract fun locationDetailsDao(): LocationDetailsLocalDataSource

    internal companion object {

        const val DB_NAME: String = "LocationDetailsDb"
    }
}