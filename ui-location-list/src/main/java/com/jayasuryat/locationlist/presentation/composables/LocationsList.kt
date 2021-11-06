package com.jayasuryat.locationlist.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.jayasuryat.locationlist.domain.model.Location

@Composable
internal fun LocationsList(
    modifier: Modifier = Modifier,
    locations: LazyPagingItems<Location>,
    onLocationClicked: (location: Location) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = locations,
            key = { location -> location.id },
        ) { location ->

            if (location == null) return@items

            LocationItem(
                location = location,
                onLocationClicked = { onLocationClicked(location) },
            )
        }
    }
}