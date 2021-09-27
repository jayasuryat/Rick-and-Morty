package com.jayasuryat.data.datasources.mappers.impl.character

import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.models.domain.Character

internal class CharacterEntityToDomainMapper : EntityToDomainMapper<CharacterEntity, Character> {

    override suspend fun invoke(input: CharacterEntity): Character = with(input) {

        return Character(
            id = id,
            name = name,
            status = Character.Status.valueOf(status.toTitleCase()),
            species = Character.Species.valueOf(species),
            type = type,
            gender = Character.Gender.valueOf(gender.toTitleCase()),
            origin = Character.Location(
                name = origin.name,
                url = origin.url,
            ),
            location = Character.Location(
                name = origin.name,
                url = origin.url,
            ),
            image = image,
            episode = episode,
            url = url,
            created = created,
        )
    }

    // TODO : Need to investigate why is this required
    private fun String.toTitleCase(): String {
        return if (this.isEmpty()) this
        else this.first().uppercase() + this.substring(1)
    }
}