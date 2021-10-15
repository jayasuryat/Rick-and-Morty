package com.jayasuryat.characterdetails.data.sources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.jayasuryat.characterdetails.CharacterDetailsEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity.Companion.CHARACTER_TABLE_NAME
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = CHARACTER_TABLE_NAME)
internal data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded(prefix = "location_") val location: Location,
    val image: String,
    val episode: List<Episode>,
) : CharacterDetailsEntity {

    internal companion object {

        internal const val CHARACTER_TABLE_NAME: String = "character_details"
    }

    internal object TypeConvert {

        @TypeConverter
        internal fun toEpisodes(episodes: String): List<Episode> = Json.decodeFromString(episodes)

        @TypeConverter
        internal fun fromEpisodes(episodes: List<Episode>): String = Json.encodeToString(episodes)
    }
}

internal data class Location(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
)

@Serializable
internal data class Episode(
    val id: Long,
    val name: String,
    val episode: String,
)