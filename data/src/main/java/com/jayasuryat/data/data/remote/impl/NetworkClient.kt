package com.jayasuryat.data.data.remote.impl

import com.jayasuryat.data.data.remote.Router
import com.jayasuryat.data.data.remote.definitions.CharactersRemoteDataSource
import com.jayasuryat.data.data.remote.definitions.EpisodesRemoteDataSource
import com.jayasuryat.data.data.remote.definitions.LocationsRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.CharacterListResponse
import com.jayasuryat.data.data.remote.dtos.EpisodesListResponse
import com.jayasuryat.data.data.remote.dtos.LocationListResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class NetworkClient(
    private val client: HttpClient
) : CharactersRemoteDataSource, EpisodesRemoteDataSource, LocationsRemoteDataSource {

    override suspend fun getCharacters(page: Int): CharacterListResponse =
        withContext(Dispatchers.IO) { client.get(block = Router.characters(page)) }

    override suspend fun getEpisodes(page: Int): EpisodesListResponse =
        withContext(Dispatchers.IO) { client.get(block = Router.episodes(page)) }

    override suspend fun getLocations(page: Int): LocationListResponse =
        withContext(Dispatchers.IO) { client.get(block = Router.locations(page)) }
}