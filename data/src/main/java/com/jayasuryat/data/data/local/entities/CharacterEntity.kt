package com.jayasuryat.data.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.jayasuryat.data.models.EntityModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = CharacterEntity.CHARACTER_TABLE_NAME)
internal data class CharacterEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded(prefix = "origin_") val origin: Location,
    @Embedded(prefix = "location_") val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) : EntityModel {

    internal data class Location(
        val name: String,
        val url: String,
    )

    internal object Converter {

        @TypeConverter
        internal fun toEpisodes(episodes: String): List<String> = Json.decodeFromString(episodes)

        @TypeConverter
        internal fun fromEpisodes(status: List<String>): String = Json.encodeToString(status)
    }

    internal companion object {

        internal const val CHARACTER_TABLE_NAME: String = "character"
    }
}