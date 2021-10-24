package com.jayasuryat.locationlist.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.locationlist.LocationListEntity
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity.Companion.LOCATION_TABLE_NAME


@Entity(tableName = LOCATION_TABLE_NAME)
internal data class LocationEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
) : LocationListEntity {

    internal companion object {

        internal const val LOCATION_TABLE_NAME: String = "locations"
    }
}
