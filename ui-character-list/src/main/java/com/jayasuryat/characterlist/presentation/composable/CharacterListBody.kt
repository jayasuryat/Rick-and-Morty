package com.jayasuryat.characterlist.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.characterlist.R
import com.jayasuryat.characterlist.event.CharacterListEvent
import com.jayasuryat.characterlist.presentation.CharacterDef
import com.jayasuryat.event.EventListener
import com.jayasuryat.event.noOpEventListener
import com.jayasuryat.sharedcomposable.composable.TopBar
import com.jayasuryat.themepreview.PreviewTheme
import kotlinx.coroutines.flow.flowOf


@Composable
internal fun CharacterListBody(
    characters: LazyPagingItems<CharacterDef>,
    eventListener: EventListener<CharacterListEvent>,
) {

    fun postEvent(event: CharacterListEvent) {
        eventListener.onEvent(event = event)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(
                top = 16.dp,
                start = 24.dp,
                end = 24.dp,
            ),
    ) {

        TopBar(
            title = stringResource(R.string.characters),
            icon = Icons.Filled.KeyboardArrowLeft,
            onIconClicked = { postEvent(CharacterListEvent.OnBackClicked) },
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        CharacterList(
            characters = characters
        ) { character ->
            postEvent(CharacterListEvent.OpenCharacter(characterId = character.id))
        }
    }
}

@Preview(name = "Character list screen [light]")
@Preview(
    name = "Character list screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    val data: List<CharacterDef> = listOf()
    val items = flowOf(PagingData.from(data)).collectAsLazyPagingItems()

    PreviewTheme {
        CharacterListBody(
            characters = items,
            eventListener = noOpEventListener(),
        )
    }
}