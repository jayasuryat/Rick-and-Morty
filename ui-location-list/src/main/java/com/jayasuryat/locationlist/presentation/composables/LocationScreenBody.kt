package com.jayasuryat.locationlist.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.jayasuryat.locationlist.R
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.sharedcomposable.composable.TopBar
import com.jayasuryat.themepreview.PreviewTheme
import kotlinx.coroutines.flow.flowOf


@Composable
internal fun LocationScreenBody(
    locations: LazyPagingItems<Location>,
    onBackClicked: () -> Unit,
    onLocationClicked: (location: Location) -> Unit,
) {

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
            title = stringResource(R.string.locations),
            icon = R.drawable.icon_back,
            onIconClicked = onBackClicked
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        LocationsList(
            locations = locations,
            onLocationClicked = { location -> onLocationClicked(location) },
        )
    }
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