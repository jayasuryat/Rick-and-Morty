package com.jayasuryat.characterdetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val charactersRepository: CharacterDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _obsCharacter: MutableLiveData<CharacterDetails> = MutableLiveData()
    val obsCharacter: LiveData<CharacterDetails> = _obsCharacter

    private val _obsEpisodes: MutableLiveData<List<EpisodeData>> = MutableLiveData()
    val obsEpisodes: LiveData<List<EpisodeData>> = _obsEpisodes

    init {
        ioScope.launch { doWhileLoading { loadCharacterDetails() } }
    }

    private suspend fun loadCharacterDetails() {

        val characterId = savedStateHandle.get<Long>("id") ?: 1

        val cacheCharacter = charactersRepository
            .getCharacterDetailsFromCache(characterId)
            .getOrNull()

        val character = cacheCharacter ?: charactersRepository.getCharacterDetails(characterId)
            .logError()
            .getOrNull() ?: return

        _obsCharacter.postValue(character)
        loadEpisodes(character)
    }

    private fun loadEpisodes(character: CharacterDetails) {

        character.episode.map { episode ->

            val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
            val episodeNum = episode.episode.substring(
                episode.episode.indexOf('E') + 1,
                episode.episode.length
            ).toInt()

            EpisodeData(
                episodeId = episode.id,
                episodeName = episode.name,
                season = seasonNum,
                episode = episodeNum,
            )
        }.let(_obsEpisodes::postValue)
    }
}