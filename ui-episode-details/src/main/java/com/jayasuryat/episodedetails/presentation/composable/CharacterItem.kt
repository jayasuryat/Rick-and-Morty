package com.jayasuryat.episodedetails.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterItem(
    character: Character,
    onCharacterClicked: () -> Unit,
) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onCharacterClicked() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.large,
            )
    ) {

        Image(
            painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
                .data(data = character.image)
                .apply(block = fun ImageRequest.Builder.() { crossfade(true) })
                .build()),
            contentDescription = "${character.name} image",
        )

        Spacer(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background.copy(alpha = 0.6f))
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .align(alignment = Alignment.Center)
        )
    }
}

@Preview(
    name = "Item character [light]",
    heightDp = 300,
)
@Preview(
    "Item character [dark]",
    heightDp = 300,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {

    val character = episodeData.characters.firstOrNull() ?: return

    PreviewTheme {
        CharacterItem(
            character = character,
            onCharacterClicked = {},
        )
    }
}