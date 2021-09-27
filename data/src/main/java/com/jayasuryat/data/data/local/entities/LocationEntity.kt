package com.jayasuryat.data.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.data.models.EntityModel


@Entity(tableName = LocationEntity.LOCATION_TABLE_NAME)
internal data class LocationEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
) : EntityModel {

    internal companion object {

        const val LOCATION_TABLE_NAME: String = "location"
    }
}
