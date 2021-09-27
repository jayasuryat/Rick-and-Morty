package com.jayasuryat.data.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.data.models.EntityModel


@Entity(tableName = EpisodeEntity.EPISODE_TABLE_NAME)
internal data class EpisodeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String,
) : EntityModel {

    internal companion object {

        const val EPISODE_TABLE_NAME: String = "episode"
    }
}