package com.jayasuryat.characterdetails.data.sources.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jayasuryat.characterdetails.CharacterDetailsEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity.Companion.CHARACTER_TABLE_NAME


@Entity(tableName = CHARACTER_TABLE_NAME)
internal data class CharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String,
    val type: String,
    @Embedded(prefix = "location_") val location: Location,
    @Embedded(prefix = "origin_") val origin: Location,
) : CharacterDetailsEntity {

    internal companion object {

        internal const val CHARACTER_TABLE_NAME: String = "character_details"
    }
}

internal data class Location(
    val id: String?,
    val name: String?,
    val type: String?,
    val dimension: String?,
)