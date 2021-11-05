package com.jayasuryat.characterlist.presentation.composable


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.jayasuryat.characterlist.presentation.CharacterDef


@Composable
internal fun CharacterList(
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

            if (character == null) return@items

            CharacterListItem(
                character = character,
                onCharacterClicked = { onClick(character) }
            )
        }
    }
}