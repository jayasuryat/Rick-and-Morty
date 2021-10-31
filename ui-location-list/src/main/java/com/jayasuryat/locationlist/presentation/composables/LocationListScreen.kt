package com.jayasuryat.locationlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.event.EventListener
import com.jayasuryat.event.noOpEventListener
import com.jayasuryat.locationlist.R
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.locationlist.presentation.LocationListViewModel
import com.jayasuryat.locationlist.presentation.event.LocationListEvent
import com.jayasuryat.sharedcomposable.composable.TopBar
import kotlinx.coroutines.flow.flowOf


@Composable
fun LocationList(
    eventListener: EventListener<LocationListEvent>,
) {

    val viewModel: LocationListViewModel = hiltViewModel()

    val locations: LazyPagingItems<Location> = viewModel.locationList.collectAsLazyPagingItems()

    Screen(
        locations = locations,
        eventListener = eventListener,
    )
}

@Composable
private fun Screen(
    locations: LazyPagingItems<Location>,
    eventListener: EventListener<LocationListEvent>,
) {

    fun postEvent(event: LocationListEvent) {
        eventListener.onEvent(event = event)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(
                top = 16.dp,
                start = 24.dp,
                end = 24.dp,
            ),
    ) {

        TopBar(
            title = stringResource(R.string.locations),
            icon = R.drawable.icon_back,
        ) {
            postEvent(LocationListEvent.OnBackPressed)
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        Locations(
            locations = locations
        ) { location ->
            postEvent(LocationListEvent.OpenLocation(location = location))
        }
    }
}

@Preview
@Composable
private fun Prev_Screen() {

    val data: List<Location> = listOf()
    val items = flowOf(PagingData.from(data)).collectAsLazyPagingItems()

    Screen(
        locations = items,
        eventListener = noOpEventListener(),
    )
}