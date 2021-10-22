package com.jayasuryat.characterdetails.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.jayasuryat.characterdetails.CharacterDetailsEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = CharacterEpisodesEntity.CHARACTER_EPISODE_TABLE_NAME)
internal data class CharacterEpisodesEntity(
    @PrimaryKey val characterId: Long,
    val episodeIds: List<Long>,
) : CharacterDetailsEntity {

    internal object TypeConverter {

        @androidx.room.TypeConverter
        fun toEpisodes(episodeIds: String): List<Long> = Json.decodeFromString(episodeIds)

        @androidx.room.TypeConverter
        fun fromEpisodes(episodeIds: List<Long>): String = Json.encodeToString(episodeIds)
    }

    internal companion object {

        internal const val CHARACTER_EPISODE_TABLE_NAME: String = "character_episode"
    }
}