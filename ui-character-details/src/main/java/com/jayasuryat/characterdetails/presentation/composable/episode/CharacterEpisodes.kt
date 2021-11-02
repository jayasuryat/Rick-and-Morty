package com.jayasuryat.characterdetails.presentation.composable.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodesViewModel
import com.jayasuryat.characterdetails.presentation.event.CharacterEpisodesEvent
import com.jayasuryat.event.EventListener

@Composable
fun CharacterEpisodesScreen(
    viewModel: CharacterEpisodesViewModel,
    eventListener: EventListener<CharacterEpisodesEvent>,
) {

    val episodeList by viewModel.obsEpisodes.observeAsState()

    val episodes = episodeList ?: return

    CharacterEpisodesBody(
        episodes = episodes,
        onBackClicked = {
            eventListener.onEvent(CharacterEpisodesEvent.OnBackClicked)
        },
        onEpisodeClicked = { episode ->
            eventListener.onEvent(CharacterEpisodesEvent.OpenEpisode(episode.episodeId))
        },
    )
}

@Preview
@Composable
private fun Prev_Character_Episodes(
    @PreviewParameter(CharacterEpisodeParameterProvider::class) episodes: List<CharacterEpisodeData>,
) {
    CharacterEpisodesBody(
        episodes = episodes,
        onBackClicked = {},
        onEpisodeClicked = {},
    )
}
