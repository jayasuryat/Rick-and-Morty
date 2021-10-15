package com.jayasuryat.episodedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.datasources.definitions.EpisodesRepository
import com.jayasuryat.data.models.domain.Character
import com.jayasuryat.data.models.domain.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository,
    private val charactersRepository: CharactersRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _obsEpisode: MutableLiveData<EpisodeData> = MutableLiveData()
    internal val obsEpisode: LiveData<EpisodeData> = _obsEpisode

    private val _obsCharacter: MutableLiveData<List<Character>> = MutableLiveData()
    internal val obsCharacter: LiveData<List<Character>> = _obsCharacter

    init {
        ioScope.launch { doWhileLoading { loadEpisodeDetails() } }
    }

    private suspend fun loadEpisodeDetails() {

        val episodeId = savedStateHandle.get<Long>("episodeId") ?: return

        val episode = episodesRepository.getEpisodeFromCache(episodeId).getOrNull() ?: return

        val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
        val episodeNum = episode.episode.substring(
            episode.episode.indexOf('E') + 1,
            episode.episode.length
        ).toInt()

        val episodeData = EpisodeData(
            episodeData = episode,
            season = "S${seasonNum}",
            episode = "E${episodeNum}"
        )

        _obsEpisode.postValue(episodeData)

        loadCharacters(episode)
    }

    private suspend fun loadCharacters(episode: Episode) {

        val characterIds = episode.characters.map { url ->
            val id = url.substring(url.lastIndexOf('/') + 1, url.length)
            id.toLong()
        }

        val characters = charactersRepository.getCharactersForIds(characterIds).getOrNull()
        _obsCharacter.postValue(characters)
    }
}