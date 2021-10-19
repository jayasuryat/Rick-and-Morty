package com.jayasuryat.episodedetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.domain.repositories.EpisodeDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsRepo: EpisodeDetailsRepository,
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

        val episode = episodeDetailsRepo.getEpisodeDetails(episodeId)
            .logError()
            .getOrNull() ?: return

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
        _obsCharacter.postValue(episode.characters)
    }
}