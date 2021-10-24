package com.jayasuryat.locationlist.data.sources.local.definitions

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity


@Dao
internal interface LocationListLocalDataSource {

    @Query("SELECT * FROM locations")
    fun getPagedLocations(): PagingSource<Int, LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocations(locations: List<LocationEntity>)

    @Query("DELETE FROM locations")
    suspend fun deleteAllLocations()
}