package com.jayasuryat.episodedetails.presentation.composable

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

@Preview
@Composable
private fun Prev_Episode_Details(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {

    EpisodeDetailsBody(
        episodeData = episodeData,
        onBackClicked = {},
        onCharacterClicked = {},
    )
}