package com.jayasuryat.characterlist.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.apollographql.apollo.exception.ApolloNetworkException
import com.bumptech.glide.load.HttpException
import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.characterlist.CharacterListQuery
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterlist.data.sources.remote.definitions.CharacterListNetworkDataSource
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
internal class CharacterListRemoteMediator(
    private val networkClient: CharacterListNetworkDataSource,
    private val cacheClient: CharacterListLocalDataSource,
    private val characterDtoToEntityMapper: Mapper<CharacterListQuery.Result, CharacterEntity>,
) : RemoteMediator<Int, CharacterEntity>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.SKIP_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {

        return try {

            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> REMOTE_API_PAGE_START_INDEX
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (state.getPageNumber() ?: 0) + 1
            }

            if (loadType == LoadType.REFRESH) cacheClient.deleteAllCharacters()

            val characters = networkClient.getCharacters(loadKey).data
                ?.characters?.results?.filterNotNull()
            
            characters?.let {
                cacheClient.saveCharacters(characterDtoToEntityMapper.map(characters))
            }

            MediatorResult.Success(endOfPaginationReached = characters.isNullOrEmpty())

        } catch (ex: ApolloNetworkException) {
            MediatorResult.Error(ex)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private fun PagingState<Int, CharacterEntity>.getPageNumber(): Int? {
        val last = lastItemOrNull() ?: return null
        val position = last.id.toInt()
        val size = config.pageSize
        val currentPage = (position / size) + (if (position % size == 0) -1 else 0)
        return currentPage + REMOTE_API_PAGE_START_INDEX
    }

    private companion object {

        const val REMOTE_API_PAGE_START_INDEX: Int = 1
    }
}