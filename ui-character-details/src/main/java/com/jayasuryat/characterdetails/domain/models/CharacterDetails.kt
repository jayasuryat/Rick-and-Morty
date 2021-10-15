package com.jayasuryat.characterdetails.domain.models

import com.jayasuryat.characterdetails.CharacterDetailsDomainModel


data class CharacterDetails(
    val id: String,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
    val location: Location,
    val image: String,
    val episode: List<Episode>,
) : CharacterDetailsDomainModel {

    // region : Enums
    enum class Gender(value: String) {
        Female("Female"),
        Male("Male"),
        Genderless("Genderless"),
        Unknown("unknown"),
    }

    enum class Species(value: String) {
        Alien("Alien"),
        Human("Human"),
        Humanoid("Humanoid"),
        Other("Other"),
    }

    enum class Status(value: String) {
        Alive("Alive"),
        Dead("Deadd"),
        Unknown("unknown"),
    }
    // endregion
}

data class Episode(
    val id: Long,
    val name: String,
    val episode: String,
)

data class Location(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
)
