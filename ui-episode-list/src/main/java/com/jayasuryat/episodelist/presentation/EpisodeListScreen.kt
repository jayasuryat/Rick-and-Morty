package com.jayasuryat.episodelist.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.episodelist.event.EpisodeListEvent
import com.jayasuryat.episodelist.event.EpisodeListEvent.OnBackClicked
import com.jayasuryat.episodelist.event.EpisodeListEvent.OpenEpisode
import com.jayasuryat.episodelist.presentation.composable.EpisodeListBody
import com.jayasuryat.episodelist.presentation.composable.EpisodesParameterProvider
import com.jayasuryat.event.EventListener
import com.jayasuryat.themepreview.PreviewTheme


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

@Preview(name = "Episode list screen [light]")
@Preview(
    name = "Episode list screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(EpisodesParameterProvider::class) episodes: List<EpisodeListData>,
) {
    PreviewTheme {
        EpisodeListBody(
            episodes = episodes,
            onBackClicked = {},
            onEpisodeClicked = {},
            onSeasonClicked = {},
        )
    }
}