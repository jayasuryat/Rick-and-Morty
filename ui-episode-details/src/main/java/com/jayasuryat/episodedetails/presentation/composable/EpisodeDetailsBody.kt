package com.jayasuryat.episodedetails.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.episodedetails.R
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.sharedcomposable.composable.TopBar
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun EpisodeDetailsBody(
    episodeData: EpisodeData,
    onBackClicked: () -> Unit,
    onCharacterClicked: (character: Character) -> Unit,
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
            title = stringResource(R.string.episode),
            icon = Icons.Filled.KeyboardArrowLeft,
            onIconClicked = onBackClicked,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        Text(
            text = episodeData.episodeName,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colors.primary)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        EpisodeInfo(episodeData = episodeData)

        Spacer(modifier = Modifier.height(8.dp))

        CharacterList(
            characters = episodeData.characters,
            onCharacterClicked = { character -> onCharacterClicked(character) }
        )
    }
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