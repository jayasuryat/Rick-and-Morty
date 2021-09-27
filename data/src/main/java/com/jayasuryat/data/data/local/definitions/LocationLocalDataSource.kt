package com.jayasuryat.data.data.local.definitions

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.data.data.local.entities.LocationEntity

internal interface LocationLocalDataSource {

    @Query("SELECT * FROM location")
    suspend fun getAllLocations(): LocationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocations(characters: List<LocationEntity>)
}