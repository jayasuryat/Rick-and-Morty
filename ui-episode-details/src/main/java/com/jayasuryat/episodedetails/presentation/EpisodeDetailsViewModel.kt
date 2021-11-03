package com.jayasuryat.episodedetails.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.episodedetails.domain.repositories.EpisodeDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsRepo: EpisodeDetailsRepository,
) : BaseViewModel() {

    private val _obsEpisodeData: MutableLiveData<EpisodeData> = MutableLiveData()
    internal val obsEpisodeData: LiveData<EpisodeData> = _obsEpisodeData

    fun loadEpisodeDetails(episodeId: Long) {
        ioScope.launch { doWhileLoading { getEpisodeDetails(episodeId) } }
    }

    private suspend fun getEpisodeDetails(episodeId: Long) {

        val episode = episodeDetailsRepo.getEpisodeDetails(episodeId)
            .logError()
            .getOrNull() ?: return

        val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
        val episodeNum = episode.episode.substring(
            episode.episode.indexOf('E') + 1,
            episode.episode.length
        ).toInt()

        val episodeData = EpisodeData(
            episodeName = episode.name,
            episode = "E${episodeNum}",
            season = "S${seasonNum}",
            episodeAiredOn = episode.airDate,
            characters = episode.characters,
        )

        _obsEpisodeData.postValue(episodeData)
    }
}