package com.jayasuryat.characterdetails.presentation.episodes.composable

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData
import com.jayasuryat.themepreview.PreviewTheme

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

@Preview(name = "Character episodes list [light]")
@Preview(
    name = "Character episodes list [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(CharacterEpisodeParameterProvider::class) episodes: List<CharacterEpisodeData>,
) {
    PreviewTheme {
        CharacterEpisodesList(
            episodes = episodes,
            onEpisodeClicked = {},
        )
    }
}