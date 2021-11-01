package com.jayasuryat.characterdetails.presentation.composable.character

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails


@Composable
internal fun CharacterDetailsBody(
    character: CharacterDetails,
    onBackPressed: () -> Unit,
    onLocationClicked: () -> Unit,
    onOriginClicked: () -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical,
            )
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
    ) {

        CharacterImageWithBackButton(
            character = character,
            onBackPressed = onBackPressed,
        )

        CharacterInfo(
            character = character,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        InfoChip(
            title = stringResource(id = R.string.location),
            value = character.location?.name ?: "<Location Unknown>",
            subText = character.location?.dimension ?: "<Dimension Unknown>",
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = onLocationClicked,
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        InfoChip(
            title = stringResource(id = R.string.origin),
            value = character.origin?.name ?: "<Location Unknown>",
            subText = character.origin?.dimension ?: "<Dimension Unknown>",
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = onOriginClicked,
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.episodes),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(color = MaterialTheme.colors.primary)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun Prev_Screen(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterDetailsBody(
        character = character,
        onBackPressed = {},
        onLocationClicked = {},
        onOriginClicked = {},
    )
}