package com.jayasuryat.characterdetails.presentation.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.characterdetails.domain.models.Episode
import com.jayasuryat.characterdetails.domain.repos.CharacterDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterEpisodesViewModel @Inject constructor(
    private val charactersRepository: CharacterDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _obsEpisodes: MutableLiveData<List<EpisodeData>> = MutableLiveData()
    val obsEpisodes: LiveData<List<EpisodeData>> = _obsEpisodes

    init {
        ioScope.launch { doWhileLoading { getEpisodes() } }
    }

    private suspend fun getEpisodes() {

        val characterId = savedStateHandle.get<Long>("id") ?: return

        val episodes = charactersRepository.getCharacterEpisodes(characterId)
            .logError()
            .getOrNull()
            ?.mapped()
            ?: return

        _obsEpisodes.postValue(episodes)
    }

    private fun List<Episode>.mapped(): List<EpisodeData> = map { episode ->

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
    }
}