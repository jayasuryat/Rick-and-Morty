package com.jayasuryat.characterlist.presentation.composable

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jayasuryat.characterlist.presentation.CharacterDef
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterListItem(
    character: CharacterDef,
    onCharacterClicked: () -> Unit,
) {

    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onCharacterClicked() }
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp),
    ) {

        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = "${character.name} image",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = MaterialTheme.shapes.large)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.large,
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = character.name,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Alignment.Start),
            )
        }
    }
}

@Preview(name = "Character list item [light]")
@Preview(
    name = "Character list item [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    PreviewTheme {

        val character = CharacterDef(
            id = 1,
            name = "Rick sanchez",
            imageUrl = "",
        )

        CharacterListItem(
            character = character,
            onCharacterClicked = {},
        )
    }
}