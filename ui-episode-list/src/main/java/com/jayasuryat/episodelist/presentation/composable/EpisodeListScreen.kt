package com.jayasuryat.episodelist.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.episodelist.presentation.EpisodeListData
import com.jayasuryat.episodelist.presentation.EpisodesListViewModel
import com.jayasuryat.episodelist.presentation.event.EpisodeListEvent
import com.jayasuryat.episodelist.presentation.event.EpisodeListEvent.OnBackClicked
import com.jayasuryat.episodelist.presentation.event.EpisodeListEvent.OpenEpisode
import com.jayasuryat.event.EventListener


@Composable
fun EpisodeListScreen(
    viewModel: EpisodesListViewModel,
    eventListener: EventListener<EpisodeListEvent>,
) {

    val episodesList by viewModel.obsEpisodes.observeAsState()
    val episodes = episodesList ?: return

    EpisodeListBody(
        episodes = episodes,
        onBackClicked = {
            val event = OnBackClicked
            eventListener.onEvent(event = event)
        },
        onEpisodeClicked = { episode ->
            val event = OpenEpisode(episodeId = episode.episodeId)
            eventListener.onEvent(event = event)
        },
        onSeasonClicked = { season ->
            viewModel.onSeasonClicked(season = season)
        },
    )
}

@Preview
@Composable
private fun Prev_Episode_List(
    @PreviewParameter(EpisodesParameterProvider::class) episodes: List<EpisodeListData>,
) {

    EpisodeListBody(
        episodes = episodes,
        onBackClicked = {},
        onEpisodeClicked = {},
        onSeasonClicked = {},
    )
}