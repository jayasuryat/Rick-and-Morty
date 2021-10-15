package com.jayasuryat.characterlist.data.sources.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.characterlist.CharacterListEntity
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity.Companion.CHARACTER_TABLE_NAME


@Entity(tableName = CHARACTER_TABLE_NAME)
internal data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val imageUrl: String,
) : CharacterListEntity {

    internal companion object {

        internal const val CHARACTER_TABLE_NAME: String = "characters"
    }
}
