package com.jayasuryat.episodedetails.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.jayasuryat.episodedetails.EpisodeDetailsEntityModel
import com.jayasuryat.episodedetails.data.sources.local.entities.EpisodeDetailsEntity.Companion.CHARACTER_TABLE_NAME
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(tableName = CHARACTER_TABLE_NAME)
data class EpisodeDetailsEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Character>,
) : EpisodeDetailsEntityModel {

    internal object Converter {

        @TypeConverter
        internal fun toCharacterDetails(characters: String): List<Character> =
            Json.decodeFromString(characters)

        @TypeConverter
        internal fun fromEpisodeList(characters: List<Character>): String =
            Json.encodeToString(characters)
    }

    internal companion object {

        internal const val CHARACTER_TABLE_NAME: String = "episode_details"
    }
}

@Serializable
data class Character(
    val id: Long,
    val name: String,
    val image: String,
)