package com.jayasuryat.data.models.domain

import com.jayasuryat.data.models.DomainModel


public data class Character(
    val id: Long,
    val name: String,
    val status: Status,
    val speciesType: Species,
    val speciesName: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) : DomainModel {

    public data class Location(
        val name: String,
        val url: String,
    )

    // region : Enums
    public enum class Gender(value: String) {
        Female("Female"),
        Male("Male"),
        Genderless("Genderless"),
        Unknown("unknown"),
    }

    public enum class Species(value: String) {
        Alien("Alien"),
        Human("Human"),
        Humanoid("Humanoid"),
        Other("Other"),
    }

    public enum class Status(value: String) {
        Alive("Alive"),
        Dead("Deadd"),
        Unknown("unknown"),
    }
    // endregion
}