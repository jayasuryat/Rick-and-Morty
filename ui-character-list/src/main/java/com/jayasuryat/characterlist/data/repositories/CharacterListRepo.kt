package com.jayasuryat.characterlist.data.repositories

import androidx.paging.*
import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.domain.models.Character
import com.jayasuryat.characterlist.domain.repos.definitions.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
internal class CharacterListRepo constructor(
    private val mediator: RemoteMediator<Int, CharacterEntity>,
    private val cacheClient: CharacterListLocalDataSource,
    private val characterEntityToDomainMapper: Mapper<CharacterEntity, Character>,
) : CharacterListRepository {

    override fun getPagedCharacters(): Flow<PagingData<Character>> {

        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
        )

        return Pager(
            config = pagingConfig,
            remoteMediator = mediator,
        ) {
            cacheClient.getPagedCharacters()
        }.flow.map {
            it.map(characterEntityToDomainMapper::map)
        }
    }

    private companion object {

        const val PAGE_SIZE: Int = 20
    }
}