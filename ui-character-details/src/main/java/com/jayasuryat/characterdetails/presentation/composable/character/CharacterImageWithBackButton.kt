package com.jayasuryat.characterdetails.presentation.composable.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.transform.RoundedCornersTransformation
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails

@Composable
internal fun CharacterImageWithBackButton(
    character: CharacterDetails,
    onBackPressed: () -> Unit,
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {

        CharacterImage(
            character = character,
        )

        BackButton(
            modifier = Modifier
                .align(alignment = Alignment.TopStart),
            onBackPressed = onBackPressed,
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
            .clip(RoundedCornerShape(32.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(32.dp),
            )
    )
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
) {

    IconButton(
        modifier = modifier
            .size(60.dp)
            .padding(start = 8.dp, top = 8.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.primary)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = CircleShape,
            ),
        onClick = { onBackPressed() },
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

@Preview
@Composable
private fun Prev_CharacterImage(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterImageWithBackButton(
        character = character,
        onBackPressed = {},
    )
}

@Preview
@Composable
private fun Prev_Back_Button() {
    BackButton {}
}

@Preview
@Composable
private fun Prev_Image(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {
    CharacterImage(character = character)
}