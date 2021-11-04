package com.jayasuryat.locationdetails.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.event.EventListener
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.locationdetails.presentation.LocationDetailsViewModel
import com.jayasuryat.locationdetails.presentation.event.LocationDetailsEvent
import com.jayasuryat.locationdetails.presentation.event.LocationDetailsEvent.OnBackClicked
import com.jayasuryat.locationdetails.presentation.event.LocationDetailsEvent.OpenCharacter


@Composable
fun LocationDetailsScreen(
    viewModel: LocationDetailsViewModel,
    eventListener: EventListener<LocationDetailsEvent>,
) {

    val locationData by viewModel.obsLocation.observeAsState()

    val location = locationData ?: return

    LocationDetailsBody(
        locationDetails = location,
        onBackClicked = { eventListener.onEvent(OnBackClicked) },
        onCharacterClicked = { character ->
            val event = OpenCharacter(characterId = character.id)
            eventListener.onEvent(event = event)
        }
    )
}

@Preview
@Composable
private fun Prev_Location_Details(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    LocationDetailsBody(
        locationDetails = locationDetails,
        onBackClicked = {},
        onCharacterClicked = {},
    )
}