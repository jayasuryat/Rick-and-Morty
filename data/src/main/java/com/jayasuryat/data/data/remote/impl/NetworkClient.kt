package com.jayasuryat.data.data.remote.impl

import android.util.Log
import com.jayasuryat.data.data.remote.Router
import com.jayasuryat.data.data.remote.definitions.CharactersRemoteDataSource
import com.jayasuryat.data.data.remote.definitions.EpisodesRemoteDataSource
import com.jayasuryat.data.data.remote.definitions.LocationsRemoteDataSource
import com.jayasuryat.data.data.remote.dtos.*
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class NetworkClient(
    private val client: HttpClient
) : CharactersRemoteDataSource, EpisodesRemoteDataSource, LocationsRemoteDataSource {

    override suspend fun getCharacters(page: Int): CharacterListResponse =
        withContext(Dispatchers.IO) {
            val data = client.get<CharacterListResponse>(block = Router.characters(page))
            Log.d("Help", "Got data : $data")
            data
        }

    override suspend fun getCharactersById(ids: List<Long>): List<CharacterDto> =
        withContext(Dispatchers.IO) { client.get(block = Router.characters(ids)) }

    override suspend fun getEpisodes(page: Int): EpisodesListResponse =
        withContext(Dispatchers.IO) { client.get(block = Router.episodes(page)) }

    override suspend fun getEpisodesFor(episodeNumbers: List<Long>): List<EpisodeDto> =
        withContext(Dispatchers.IO) { client.get(block = Router.episodes(episodeNumbers)) }

    override suspend fun getLocations(page: Int): LocationListResponse =
        withContext(Dispatchers.IO) { client.get(block = Router.locations(page)) }
}