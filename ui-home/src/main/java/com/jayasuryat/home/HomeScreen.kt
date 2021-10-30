package com.jayasuryat.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jayasuryat.base.event.EventListener
import com.jayasuryat.base.event.noOpEventListener
import com.jayasuryat.home.event.HomeEvent


@Composable
fun Home(
    eventListener: EventListener<HomeEvent>,
) {

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp,
            )
    ) {

        HomeItem(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            title = "Characters",
            image = R.drawable.img_rick,
            onClick = { eventListener.onEvent(HomeEvent.OpenCharacters) },
        )
        HomeItem(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            title = "Episodes",
            image = R.drawable.img_episode,
            onClick = { eventListener.onEvent(HomeEvent.OpenEpisodes) },
        )
        HomeItem(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            title = "Locations",
            image = R.drawable.img_location,
            onClick = { eventListener.onEvent(HomeEvent.OpenLocations) },
        )
    }
}

@Composable
private fun HomeItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(MaterialTheme.shapes.large)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.large,
            )
            .clickable { onClick() }
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onPrimary,
            text = title,
        )
        Image(
            modifier = Modifier
                .padding(
                    top = 64.dp,
                    end = 16.dp,
                )
                .wrapContentSize(),
            painter = painterResource(id = image),
            contentDescription = "$title Image",
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Home(noOpEventListener())
}