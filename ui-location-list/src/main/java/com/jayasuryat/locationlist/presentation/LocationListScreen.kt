package com.jayasuryat.locationlist.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.event.EventListener
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.locationlist.event.LocationListEvent
import com.jayasuryat.locationlist.event.LocationListEvent.OnBackClicked
import com.jayasuryat.locationlist.presentation.composables.LocationScreenBody
import com.jayasuryat.themepreview.PreviewTheme
import kotlinx.coroutines.flow.flowOf


@Composable
fun LocationListScreen(
    viewModel: LocationListViewModel,
    eventListener: EventListener<LocationListEvent>,
) {

    val locations: LazyPagingItems<Location> = viewModel.locationList.collectAsLazyPagingItems()

    LocationScreenBody(
        locations = locations,
        onBackClicked = { eventListener.onEvent(OnBackClicked) },
        onLocationClicked = { location ->
            val event = LocationListEvent.OpenLocation(locationId = location.id)
            eventListener.onEvent(event = event)
        }
    )
}

@Preview(name = "Location list screen [light]")
@Preview(
    "Location list screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    val data: List<Location> = listOf()
    val items = flowOf(PagingData.from(data)).collectAsLazyPagingItems()

    PreviewTheme {
        LocationScreenBody(
            locations = items,
            onBackClicked = {},
            onLocationClicked = {},
        )
    }
}