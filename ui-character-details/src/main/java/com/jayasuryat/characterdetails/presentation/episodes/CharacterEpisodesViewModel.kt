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

    private val _obsEpisodes: MutableLiveData<List<CharacterEpisodeData>> = MutableLiveData()
    val obsEpisodes: LiveData<List<CharacterEpisodeData>> = _obsEpisodes

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

    private fun List<Episode>.mapped(): List<CharacterEpisodeData> {

        val mapped = map { episode ->

            val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
            val episodeNum = episode.episode.substring(
                episode.episode.indexOf('E') + 1,
                episode.episode.length
            ).toInt()

            CharacterEpisodeData.EpisodeData(
                episodeId = episode.id,
                episodeName = episode.name,
                season = seasonNum,
                episode = episodeNum,
            )
        }

        val seasons = mapped.groupBy { it.season }

        val episodesList: MutableList<CharacterEpisodeData> = mutableListOf()

        seasons.forEach { seasonData ->
            episodesList.addAll(seasonData.value)
            episodesList.add(CharacterEpisodeData.SeasonDivider(seasonData.key.toLong()))
        }

        episodesList.removeLast()

        return episodesList
    }
}