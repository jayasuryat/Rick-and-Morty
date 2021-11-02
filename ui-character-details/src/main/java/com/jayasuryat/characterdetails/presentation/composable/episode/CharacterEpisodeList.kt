package com.jayasuryat.characterdetails.presentation.composable.episode

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData

@Composable
internal fun CharacterEpisodesList(
    episodes: List<CharacterEpisodeData>,
    modifier: Modifier = Modifier,
    onEpisodeClicked: (episode: CharacterEpisodeData.EpisodeData) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
    ) {

        items(
            items = episodes,
            key = { episode -> episode.id }
        ) {

            when (it) {
                is CharacterEpisodeData.EpisodeData -> {
                    CharacterEpisodeItem(
                        episode = it,
                        onEpisodeClicked = onEpisodeClicked
                    )
                }

                is CharacterEpisodeData.SeasonDivider -> {
                    CharacterSeasonDivider()
                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev_Character_Episode_List(
    @PreviewParameter(CharacterEpisodeParameterProvider::class) episodes: List<CharacterEpisodeData>,
) {

    CharacterEpisodesList(
        episodes = episodes,
        onEpisodeClicked = {},
    )
}