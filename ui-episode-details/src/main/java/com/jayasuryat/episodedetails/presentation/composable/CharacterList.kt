package com.jayasuryat.episodedetails.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.jayasuryat.episodedetails.R
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.themepreview.PreviewTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CharacterList(
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
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
        ) {

            items(
                items = characters,
            ) { character ->

                CharacterItem(
                    character = character,
                    onCharacterClicked = { onCharacterClicked(character) },
                )
            }
        }
    }
}

@Preview(name = "Character list [light]")
@Preview(
    "Character list [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {

    PreviewTheme {
        CharacterList(
            characters = episodeData.characters,
            onCharacterClicked = {},
        )
    }
}