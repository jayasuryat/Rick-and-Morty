package com.jayasuryat.episodelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayasuryat.base.arch.BaseViewModel
import com.jayasuryat.episodelist.domain.models.Episode
import com.jayasuryat.episodelist.domain.repos.EpisodeListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesListViewModel @Inject constructor(
    private val episodesRepository: EpisodeListRepository,
) : BaseViewModel() {

    private val _obsEpisodes: MutableLiveData<List<EpisodeListData>> = MutableLiveData()
    internal val obsEpisodes: LiveData<List<EpisodeListData>> = _obsEpisodes

    private var rawEpisodes: List<EpisodeListData>? = null

    init {
        ioScope.launch { doWhileLoading { loadEpisodes() } }
    }

    private suspend fun loadEpisodes() {

        episodesRepository.getAllEpisodesInCache()
            .getOrNull().map()?.let { episodes ->

                rawEpisodes = episodes

                episodes.filterIsInstance<EpisodeListData.Season>()
                    .let(_obsEpisodes::postValue)
            }

        episodesRepository.getAllEpisodes()
            .logError()
            .getOrNull().map()?.let { episodes ->

                if (episodes == rawEpisodes) return@let
                rawEpisodes = episodes

                episodes.filterIsInstance<EpisodeListData.Season>()
                    .let(_obsEpisodes::postValue)
            }
    }

    internal fun onSeasonClicked(season: EpisodeListData.Season) {

        val current = _obsEpisodes.value
        if (current.isNullOrEmpty()) {
            _obsEpisodes.postValue(null)
            return
        }

        if (season.isExpanded) {

            val filtered = current.filter {
                if (it is EpisodeListData.Episode) it.seasonName != season.seasonName
                else true
            }

            val final = filtered.toMutableList()

            val seasonIndex = final.indexOf(season)
            final.removeAt(seasonIndex)
            final.add(seasonIndex, season.copy(isExpanded = !season.isExpanded))

            _obsEpisodes.postValue(final)
        } else {

            val episodes = rawEpisodes?.filter {
                it is EpisodeListData.Episode && it.seasonName == season.seasonName
            }

            val seasonIndex = current.indexOf(season)

            val final = current.toMutableList()
            episodes?.let { final.addAll(seasonIndex + 1, it) }

            final.removeAt(seasonIndex)
            final.add(seasonIndex, season.copy(isExpanded = !season.isExpanded))
            _obsEpisodes.postValue(final)
        }
    }

    private fun List<Episode>?.map(): List<EpisodeListData>? {

        data class EpisodeData(
            val id: Long,
            val episode: Episode,
            val seasonNumber: Int,
            val episodeNumber: Int,
        )

        if (this.isNullOrEmpty()) return null

        val episodes = this.map { episode ->

            val seasonNum = episode.episode.substring(1, episode.episode.indexOf('E')).toInt()
            val episodeNum = episode.episode.substring(
                episode.episode.indexOf('E') + 1,
                episode.episode.length
            ).toInt()

            EpisodeData(
                id = episode.id,
                episode = episode,
                seasonNumber = seasonNum,
                episodeNumber = episodeNum
            )
        }

        val grouped = episodes.groupBy { it.seasonNumber }

        val mapped: MutableList<EpisodeListData> = mutableListOf()

        grouped.forEach { seasonData ->

            val season = EpisodeListData.Season(
                seasonName = "Season ${seasonData.key}",
                isExpanded = false
            )
            mapped.add(season)

            val mappedEpisodes = seasonData.value.map { episode ->
                EpisodeListData.Episode(
                    episodeId = episode.id,
                    seasonName = season.seasonName,
                    episodeName = episode.episode.name,
                    episodeNumber = episode.episodeNumber,
                )
            }

            mapped.addAll(mappedEpisodes)
        }

        return mapped
    }
}