package com.jayasuryat.episodelist.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.episodelist.EpisodeListsEntityModel
import com.jayasuryat.episodelist.data.sources.local.entities.EpisodeEntity.Companion.EPISODES_TABLE_NAME


@Entity(tableName = EPISODES_TABLE_NAME)
data class EpisodeEntity(
    @PrimaryKey val id: Long,
    val episode: String,
    val name: String,
) : EpisodeListsEntityModel {

    internal companion object {

        internal const val EPISODES_TABLE_NAME: String = "episode_def"
    }
}
