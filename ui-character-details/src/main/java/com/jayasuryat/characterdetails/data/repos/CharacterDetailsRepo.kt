package com.jayasuryat.characterdetails.data.repos

import com.jayasuryat.basedata.mappers.Mapper
import com.jayasuryat.basedata.mappers.map
import com.jayasuryat.basedata.models.KResult
import com.jayasuryat.basedata.models.wrapAsResult
import com.jayasuryat.basedata.providers.DispatcherProvider
import com.jayasuryat.characterdetails.CharacterDetailsQuery
import com.jayasuryat.characterdetails.CharacterEpisodeQuery
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.CharacterEpisodesEntity
import com.jayasuryat.characterdetails.data.sources.local.entities.EpisodeEntity
import com.jayasuryat.characterdetails.data.sources.remote.definitons.CharacterDetailsRemoteDataSource
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Episode
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository


internal class CharacterDetailsRepo(
    private val dispatcher: DispatcherProvider,
    private val networkClient: CharacterDetailsRemoteDataSource,
    private val cacheClient: CharacterDetailsLocalDataSource,
    private val characterDtoToEntityMapper: Mapper<CharacterDetailsQuery.Character, CharacterEntity>,
    private val characterEntityToDomainMapper: Mapper<CharacterEntity, CharacterDetails>,
    private val episodeDtoToEntityMapper: Mapper<CharacterEpisodeQuery.Episode, EpisodeEntity>,
    private val episodeEntityToDomainMapper: Mapper<EpisodeEntity, Episode>,
) : CharacterDetailsRepository {

    override suspend fun getCharacterDetails(
        characterId: Long,
    ): KResult<CharacterDetails> = wrapAsResult(dispatcher.io()) {

        cacheClient.getCharacter(characterId)?.let { cacheCharacter ->
            return@wrapAsResult characterEntityToDomainMapper.map(cacheCharacter)
        }

        val networkResponse = networkClient.getCharacterDetails(characterId).data
        val networkCharacter = networkResponse?.character
            ?: throw RuntimeException("No characters found") // TODO: 15/10/21

        val mappedCharacter = characterDtoToEntityMapper.map(networkCharacter)
        cacheClient.saveCharacterDetails(mappedCharacter)

        val cachedCharacter = cacheClient.getCharacter(characterId)
            ?: throw IllegalStateException("Model not found in cache for id $characterId")
        characterEntityToDomainMapper.map(cachedCharacter)
    }

    override suspend fun getCharacterEpisodes(
        characterId: Long,
    ): KResult<List<Episode>> = wrapAsResult(dispatcher.io()) {

        getCharacterEpisodesFromCache(characterId)?.let { return@wrapAsResult it }

        val remoteEpisodes = networkClient.getCharacterEpisodes(characterId).data?.character
            ?: throw RuntimeException("No episodes found for character $characterId") // TODO: 15/10/21

        val episodeIds = remoteEpisodes.episode.mapNotNull { it?.id?.toLong() }
        val episodes = remoteEpisodes.episode.filterNotNull()

        val characterEpisodes = CharacterEpisodesEntity(
            characterId = characterId,
            episodeIds = episodeIds
        )

        cacheClient.saveEpisodes(episodeDtoToEntityMapper.map(episodes))
        cacheClient.saveCharacterEpisodes(characterEpisodes)

        return@wrapAsResult getCharacterEpisodesFromCache(characterId)
            ?: throw IllegalStateException("Episode model not found in cache for id $characterId")
    }

    private suspend fun getCharacterEpisodesFromCache(characterId: Long): List<Episode>? {

        val episodeIds = cacheClient.getCharacterEpisodes(characterId)?.episodeIds
        if (episodeIds.isNullOrEmpty()) return null

        val episodes = cacheClient.getEpisodes(episodeIds)
        if (episodes.isNullOrEmpty()) return null

        return episodeEntityToDomainMapper.map(episodes)
    }
}