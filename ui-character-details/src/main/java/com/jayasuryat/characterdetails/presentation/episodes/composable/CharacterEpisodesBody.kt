package com.jayasuryat.characterdetails.presentation.episodes.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData
import com.jayasuryat.sharedcomposable.composable.TopBar
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterEpisodesBody(
    episodes: List<CharacterEpisodeData>,
    onBackClicked: () -> Unit,
    onEpisodeClicked: (episode: CharacterEpisodeData.EpisodeData) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(
                top = 16.dp,
                start = 24.dp,
                end = 24.dp,
            ),
    ) {

        TopBar(
            title = stringResource(id = R.string.episodes),
            icon = Icons.Filled.KeyboardArrowLeft,
            onIconClicked = onBackClicked,
        )

        Spacer(modifier = Modifier.height(16.dp))

        CharacterEpisodesList(
            episodes = episodes,
            modifier = Modifier.fillMaxSize(),
            onEpisodeClicked = onEpisodeClicked,
        )
    }
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