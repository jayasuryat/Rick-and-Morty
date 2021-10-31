package com.jayasuryat.locationlist.data.repos

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.apollographql.apollo.exception.ApolloNetworkException
import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.locationlist.LocationListQuery
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.local.entities.LocationEntity
import com.jayasuryat.locationlist.data.sources.remote.definitions.LocationListRemoteDataSource
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
internal class LocationListRemoteMediator(
    private val networkClient: LocationListRemoteDataSource,
    private val cacheClient: LocationListLocalDataSource,
    private val locationDtoToEntityMapper: Mapper<LocationListQuery.Result, LocationEntity>,
) : RemoteMediator<Int, LocationEntity>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.SKIP_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>,
    ): MediatorResult {

        return try {

            val loadKey: Int = when (loadType) {
                LoadType.REFRESH -> REMOTE_API_PAGE_START_INDEX
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> (state.getPageNumber() ?: 0) + 1
            }

            if (loadType == LoadType.REFRESH) cacheClient.deleteAllLocations()

            val locations = networkClient.getLocations(loadKey)
                .data?.locations?.results?.filterNotNull()

            locations?.let {
                cacheClient.saveLocations(locationDtoToEntityMapper.map(locations))
            }
            MediatorResult.Success(endOfPaginationReached = locations.isNullOrEmpty())

        } catch (ex: ApolloNetworkException) {
            MediatorResult.Error(ex)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }

    private fun PagingState<Int, LocationEntity>.getPageNumber(): Int? {
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
