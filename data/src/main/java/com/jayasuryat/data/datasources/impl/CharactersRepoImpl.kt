package com.jayasuryat.data.datasources.impl

import com.jayasuryat.data.data.local.definitions.CharactersLocalDataSource
import com.jayasuryat.data.data.local.entities.CharacterEntity
import com.jayasuryat.data.data.remote.definitions.CharactersRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.CharacterDto
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.datasources.mappers.definitions.DtoToEntityMapper
import com.jayasuryat.data.datasources.mappers.definitions.EntityToDomainMapper
import com.jayasuryat.data.models.KResult
import com.jayasuryat.data.models.domain.Character
import com.jayasuryat.data.models.wrapAsResult

internal class CharactersRepoImpl(
    private val networkClient: CharactersRemoteDataSource,
    private val cacheClient: CharactersLocalDataSource,
    private val characterDtoToEntity: DtoToEntityMapper<CharacterDto, CharacterEntity>,
    private val characterEntityToDomain: EntityToDomainMapper<CharacterEntity, Character>,
) : CharactersRepository {

    override suspend fun getCharacters(page: Int): KResult<List<Character>> = wrapAsResult {

        networkClient.getCharacters(page)
            .results
            .map { dto -> characterDtoToEntity(dto) }
            .also { entities -> cacheClient.saveCharacters(entities) }

        cacheClient.getAllCharacters()
            .map { entity -> characterEntityToDomain(entity) }
    }

    override suspend fun getAllCharactersInCache(): KResult<List<Character>> = wrapAsResult {

        cacheClient.getAllCharacters()
            .map { entity -> characterEntityToDomain(entity) }
    }

    override suspend fun getCharacterFromCache(characterId: Long):
            KResult<Character> = wrapAsResult {

        val entity = cacheClient.getCharacterById(characterId)
        characterEntityToDomain(entity)
    }
}