package com.jayasuryat.locationdetails.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.event.EventListener
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.locationdetails.event.LocationDetailsEvent
import com.jayasuryat.locationdetails.event.LocationDetailsEvent.OnBackClicked
import com.jayasuryat.locationdetails.event.LocationDetailsEvent.OpenCharacter
import com.jayasuryat.locationdetails.presentation.compose.LocationDetailsBody
import com.jayasuryat.locationdetails.presentation.compose.LocationParameterProvider
import com.jayasuryat.themepreview.PreviewTheme


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

@Preview(name = "Location details screen [light]")
@Preview(
    name = "Location details screen [dark]",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    PreviewTheme {
        LocationDetailsBody(
            locationDetails = locationDetails,
            onBackClicked = {},
            onCharacterClicked = {},
        )
    }
}