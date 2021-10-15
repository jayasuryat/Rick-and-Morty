package com.jayasuryat.characterdetails.data.repos

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository


internal class CharacterDetailsRepo(
    private val dispatcher: DispatcherProvider,
    private val networkClient: CharacterDetailsRemoteDataSource,
    private val cacheClient: CharacterDetailsLocalDataSource,
    private val characterDtoToEntityMapper: Mapper<CharacterDetailsQuery.Character, CharacterEntity>,
    private val characterEntityToDomainMapper: Mapper<CharacterEntity, CharacterDetails>,
) : CharacterDetailsRepository {

    override suspend fun getCharacterDetails(
        characterId: Long,
    ): KResult<CharacterDetails> = wrapAsResult(dispatcher.io()) {

        val networkResponse = networkClient.getCharacterDetails(characterId).data
        val networkCharacters = networkResponse?.character()
            ?: throw RuntimeException("No characters found") // TODO: 15/10/21

        val mappedCharacters = characterDtoToEntityMapper(networkCharacters)
        cacheClient.saveCharacterDetails(mappedCharacters)

        val cachedCharacters = cacheClient.getCharacter(characterId)
        characterEntityToDomainMapper(cachedCharacters)
    }

    override suspend fun getCharacterDetailsFromCache(
        characterId: Long,
    ): KResult<CharacterDetails> = wrapAsResult(dispatcher.io()) {
        characterEntityToDomainMapper(cacheClient.getCharacter(characterId))
    }
}