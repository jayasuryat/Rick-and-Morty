package com.jayasuryat.locationdetails.data.sources.local.definitions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity


@Dao
internal interface LocationDetailsLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocationDetails(locationDetails: LocationDetailsEntity)

    @Query("SELECT * FROM location_details WHERE id=:locationId")
    fun getLocationDetails(locationId: Long): LocationDetailsEntity?
}