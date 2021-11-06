package com.jayasuryat.characterdetails.presentation.episodes

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.event.CharacterEpisodesEvent
import com.jayasuryat.characterdetails.presentation.episodes.composable.CharacterEpisodeParameterProvider
import com.jayasuryat.characterdetails.presentation.episodes.composable.CharacterEpisodesBody
import com.jayasuryat.event.EventListener
import com.jayasuryat.themepreview.PreviewTheme

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

@Preview(name = "Character episode screen [light]")
@Preview(
    name = "Character episode screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(CharacterEpisodeParameterProvider::class) episodes: List<CharacterEpisodeData>,
) {
    PreviewTheme {
        CharacterEpisodesBody(
            episodes = episodes,
            onBackClicked = {},
            onEpisodeClicked = {},
        )
    }
}
