package com.jayasuryat.characterlist.data.repositories

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository

internal class CharacterListRepo(
    private val dispatcher: DispatcherProvider,
    private val networkClient: CharacterListNetworkDataSource,
    private val cacheClient: CharacterListLocalDataSource,
    private val characterDtoToEntityMapper: Mapper<CharacterListQuery.Result, CharacterEntity>,
    private val characterEntityToDomainMapper: Mapper<CharacterEntity, Character>,
) : CharacterListRepository {

    override suspend fun getCharacters(
        page: Int,
    ): KResult<List<Character>> = wrapAsResult(dispatcher.io()) {

        val networkResponse = networkClient.getCharacters(page).data?.characters()
        val networkCharacters = networkResponse?.results()

        if (networkCharacters.isNullOrEmpty())
            throw RuntimeException("No characters found") // TODO: 15/10/21

        val mappedCharacters = characterDtoToEntityMapper.map(networkCharacters)
        cacheClient.saveCharacters(mappedCharacters)

        val cachedCharacters = cacheClient.getCharacters(limit = PAGE_SIZE, offset = page - 1)

        characterEntityToDomainMapper.map(cachedCharacters)
    }

    override suspend fun getAllCharactersInCache(): KResult<List<Character>> =
        wrapAsResult(dispatcher.io()) {
            characterEntityToDomainMapper.map(cacheClient.getAllCharacters())
        }

    companion object {

        private const val PAGE_SIZE: Int = 20
    }
}