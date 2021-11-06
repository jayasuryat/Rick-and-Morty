package com.jayasuryat.characterdetails.presentation.character.composable

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.themepreview.PreviewTheme

@Composable
internal fun CharacterImageWithBackButton(
    character: CharacterDetails,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {

        CharacterImage(
            character = character,
        )

        BackButton(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(start = 8.dp, top = 8.dp),
            onBackClicked = onBackClicked,
        )
    }
}

@Composable
private fun CharacterImage(
    character: CharacterDetails,
) {

    Image(
        painter = rememberImagePainter(
            data = character.image,
            builder = {
                crossfade(true)
                placeholder(R.drawable.drawable_left)
            }
        ),
        contentDescription = "${character.name} image",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape = MaterialTheme.shapes.large)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.large,
            )
    )
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
) {

    IconButton(
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.primary)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = CircleShape,
            ),
        onClick = { onBackClicked() },
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                )
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "Back",
            tint = MaterialTheme.colors.onBackground,
        )
    }
}

@Preview(name = "Character image w/ back button [light]")
@Preview(
    name = "Character image w/ back button [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewCharacterImageWithBackButton(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    PreviewTheme {
        CharacterImageWithBackButton(
            character = character,
            onBackClicked = {},
        )
    }
}

@Preview(name = "Back button [light]")
@Preview(
    name = "Back button [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewBackButton() {
    PreviewTheme {
        BackButton {}
    }
}

@Preview(name = "Character image [light]")
@Preview(
    name = "Character image [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PreviewImage(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {
    PreviewTheme {
        CharacterImage(character = character)
    }
}