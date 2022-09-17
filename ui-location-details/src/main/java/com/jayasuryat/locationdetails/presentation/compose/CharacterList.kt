package com.jayasuryat.locationdetails.presentation.compose

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jayasuryat.locationdetails.R
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.themepreview.PreviewTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LocationList(
    characters: List<Character>,
    onCharacterClicked: (character: Character) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp),
    ) {

        Text(
            text = stringResource(id = R.string.characters),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
        ) {

            items(
                items = characters,
            ) { character ->

                CharacterListItem(
                    character = character,
                    onCharacterClicked = { onCharacterClicked(character) }
                )
            }
        }
    }
}

@Preview(name = "Item Character [light]")
@Preview(
    name = "Item Character [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    PreviewTheme {
        LocationList(
            characters = locationDetails.characters,
            onCharacterClicked = {},
        )
    }
}