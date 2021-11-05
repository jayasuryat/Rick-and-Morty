package com.jayasuryat.episodedetails.presentation.composable

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.episodedetails.presentation.EpisodeDetailsViewModel
import com.jayasuryat.episodedetails.presentation.event.EpisodeDetailsEvent
import com.jayasuryat.episodedetails.presentation.event.EpisodeDetailsEvent.OnBackClicked
import com.jayasuryat.episodedetails.presentation.event.EpisodeDetailsEvent.OpenCharacter
import com.jayasuryat.event.EventListener
import com.jayasuryat.themepreview.PreviewTheme


@Composable
fun EpisodeDetailsScreen(
    viewModel: EpisodeDetailsViewModel,
    eventListener: EventListener<EpisodeDetailsEvent>,
) {

    val episodeData by viewModel.obsEpisodeData.observeAsState()

    val episode = episodeData ?: return

    EpisodeDetailsBody(
        episodeData = episode,
        onBackClicked = { eventListener.onEvent(OnBackClicked) },
        onCharacterClicked = { character ->
            val event = OpenCharacter(characterId = character.id)
            eventListener.onEvent(event = event)
        }
    )
}

@Preview(name = "Episode details screen [light]")
@Preview(
    "Episode details screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {
    PreviewTheme {
        EpisodeDetailsBody(
            episodeData = episodeData,
            onBackClicked = {},
            onCharacterClicked = {},
        )
    }
}