package com.jayasuryat.locationdetails.presentation.compose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterListItem(
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
            painter = rememberImagePainter(
                data = character.image,
                builder = {
                    crossfade(true)
                }
            ),
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
    name = "Item Character [light]",
    heightDp = 300,
)
@Preview(
    name = "Item Character [dark]",
    heightDp = 300,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    val character = locationDetails.characters.firstOrNull() ?: return

    PreviewTheme {
        CharacterListItem(
            character = character,
            onCharacterClicked = {},
        )
    }
}