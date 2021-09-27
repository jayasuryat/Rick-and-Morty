package com.jayasuryat.data.datasources.mappers.impl.character

import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.data.remote.dtos.CharacterDto
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper

internal class CharacterDtoToEntityMapper : DtoToEntityMapper<CharacterDto, CharacterEntity> {

    override suspend fun invoke(input: CharacterDto): CharacterEntity = with(input) {

        return CharacterEntity(
            id = id,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = CharacterEntity.Location(
                name = origin.name,
                url = origin.url,
            ),
            location = CharacterEntity.Location(
                name = origin.name,
                url = origin.url,
            ),
            image = image,
            episode = episode,
            url = url,
            created = created,
        )
    }
}