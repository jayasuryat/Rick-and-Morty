package com.jayasuryat.locationdetails.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.locationdetails.LocationDetailsEntityModel
import com.jayasuryat.locationdetails.data.sources.local.entities.LocationDetailsEntity.Companion.LOCATION_TABLE_NAME
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = LOCATION_TABLE_NAME)
data class LocationDetailsEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Resident>,
) : LocationDetailsEntityModel {

    object TypeConverter {

        @androidx.room.TypeConverter
        fun fromResident(residents: List<Resident>) = Json.encodeToString(residents)

        @androidx.room.TypeConverter
        fun toResidents(residents: String): List<Resident> = Json.decodeFromString(residents)
    }

    internal companion object {

        internal const val LOCATION_TABLE_NAME: String = "location_details"
    }
}

@Serializable
data class Resident(
    val id: Long,
    val name: String,
    val image: String,
)