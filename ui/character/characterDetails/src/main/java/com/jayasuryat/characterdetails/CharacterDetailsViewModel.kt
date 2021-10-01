package com.jayasuryat.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.data.datasources.definitions.CharactersRepository
import com.jayasuryat.data.datasources.definitions.EpisodesRepository
import com.jayasuryat.data.models.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _obsCharacter: MutableLiveData<Character> = MutableLiveData()
    val obsCharacter: LiveData<Character> = _obsCharacter

    private val _obsEpisodes: MutableLiveData<List<EpisodeData>> = MutableLiveData()
    val obsEpisodes: LiveData<List<EpisodeData>> = _obsEpisodes

    init {
        ioScope.launch { doWhileLoading { loadCharacterDetails() } }
    }

    private suspend fun loadCharacterDetails() {

        val characterId = savedStateHandle.get<Long>("id") ?: 1

        val character = charactersRepository
            .getCharacterFromCache(characterId = characterId)
            .getOrNull() ?: return

        _obsCharacter.postValue(character)

        loadEpisodes(character.episode)
    }

    private suspend fun loadEpisodes(episodes: List<String>?) {

        if (episodes.isNullOrEmpty()) return

        val episodeIds = episodes.map {
            it.substring(it.lastIndexOf('/') + 1, it.length).toInt().toLong()
        }

        episodesRepository.getEpisodes(episodeIds)
            .getOrNull()
            ?.map { episode ->

                val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
                val episodeNum = episode.episode.substring(
                    episode.episode.indexOf('E') + 1,
                    episode.episode.length
                ).toInt()

                EpisodeData(
                    episodeName = episode.name,
                    season = seasonNum,
                    episode = episodeNum,
                    url = episode.url
                )
            }?.let(_obsEpisodes::postValue)
    }
}