package com.jayasuryat.characterlist.presentation.composable


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.characterlist.presentation.CharacterDef
import com.jayasuryat.characterlist.presentation.CharacterListViewModel
import com.jayasuryat.characterlist.presentation.event.CharacterListEvent
import com.jayasuryat.event.EventListener
import com.jayasuryat.event.noOpEventListener
import com.jayasuryat.themepreview.PreviewThemeParamProvider
import com.jayasuryat.themepreview.PreviewThemeProvider
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

@Preview
@Composable
private fun Prev_Screen(
    @PreviewParameter(PreviewThemeParamProvider::class) themeProvider: PreviewThemeProvider,
) {

    val data: List<CharacterDef> = listOf()
    val items = flowOf(PagingData.from(data)).collectAsLazyPagingItems()

    themeProvider.Theme {
        CharacterListBody(
            characters = items,
            eventListener = noOpEventListener(),
        )
    }
}
