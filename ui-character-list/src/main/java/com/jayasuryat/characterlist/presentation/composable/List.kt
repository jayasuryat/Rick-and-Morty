package com.jayasuryat.characterlist.presentation.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.jayasuryat.characterlist.presentation.CharacterDef


@Composable
internal fun Characters(
    modifier: Modifier = Modifier,
    characters: LazyPagingItems<CharacterDef>,
    onClick: (character: CharacterDef) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = characters,
            key = { character -> character.id },
        ) { character ->

            character?.let {
                CharacterItem(character = it) { character ->
                    onClick(character)
                }
            }
        }
    }
}

@Composable
private fun CharacterItem(
    character: CharacterDef,
    onClick: (character: CharacterDef) -> Unit,
) {

    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(32.dp))
            .clickable { onClick(character) }
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp),
    ) {

        Image(
            painter = rememberImagePainter(character.imageUrl),
            contentDescription = "${character.name} image",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(32.dp),
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(CenterVertically)
        ) {
            Text(
                text = character.name,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(alignment = Start),
            )
        }
    }
}


@Preview
@Composable
private fun Prev_Character_Item() {

    val character = CharacterDef(
        id = 1,
        name = "Rick sanchez",
        imageUrl = "",
    )

    CharacterItem(
        character = character,
    ) {}
}
