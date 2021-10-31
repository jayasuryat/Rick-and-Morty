package com.jayasuryat.locationlist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.jayasuryat.locationlist.domain.model.Location

@Composable
internal fun Locations(
    modifier: Modifier = Modifier,
    locations: LazyPagingItems<Location>,
    onClick: (location: Location) -> Unit,
) {

    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = locations,
            key = { location -> location.id },
        ) { location ->
            location?.let {

                LocationItem(location = it) { location ->
                    onClick(location)
                }
            }
        }
    }
}

@Composable
private fun LocationItem(
    location: Location,
    onClick: (location: Location) -> Unit,
) {

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(32.dp))
            .clickable { onClick(location) }
            .background(color = MaterialTheme.colors.primary)
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp,
            ),
    ) {

        Surface(
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.background,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .wrapContentSize()
                .size(32.dp),
        ) {
            Text(
                text = location.id.toString(),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentSize()
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = location.name,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = location.dimension,
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}


@Preview
@Composable
private fun Prev_Location_Item() {
    LocationItem(
        location = Location(
            id = 1,
            name = "Location name",
            type = "Location type",
            dimension = "Location dimension",
        )
    ) {}
}