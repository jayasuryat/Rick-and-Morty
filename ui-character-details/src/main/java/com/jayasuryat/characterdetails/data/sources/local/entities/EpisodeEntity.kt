package com.jayasuryat.characterdetails.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.characterdetails.CharacterDetailsEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity.Companion.CHARACTER_EPISODE_TABLE_NAME

@Entity(tableName = CHARACTER_EPISODE_TABLE_NAME)
internal data class EpisodeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val episode: String,
) : CharacterDetailsEntity {

    internal companion object {

        internal const val CHARACTER_EPISODE_TABLE_NAME: String = "episode"
    }
}