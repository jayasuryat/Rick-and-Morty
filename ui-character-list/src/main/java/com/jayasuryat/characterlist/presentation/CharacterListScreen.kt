package com.jayasuryat.characterlist.presentation


import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.characterlist.event.CharacterListEvent
import com.jayasuryat.characterlist.presentation.composable.CharacterListBody
import com.jayasuryat.event.EventListener
import com.jayasuryat.event.noOpEventListener
import com.jayasuryat.themepreview.PreviewTheme
import kotlinx.coroutines.flow.flowOf


@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel,
    eventListener: EventListener<CharacterListEvent>,
) {

    val characters: LazyPagingItems<CharacterDef> =
        viewModel.charactersList.collectAsLazyPagingItems()

    CharacterListBody(
        characters = characters,
        eventListener = eventListener,
    )
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