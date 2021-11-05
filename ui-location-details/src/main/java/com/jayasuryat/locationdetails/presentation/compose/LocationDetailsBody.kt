package com.jayasuryat.locationdetails.presentation.compose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.locationdetails.R
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.sharedcomposable.composable.TopBar
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun LocationDetailsBody(
    locationDetails: LocationDetails,
    onBackClicked: () -> Unit,
    onCharacterClicked: (character: Character) -> Unit,
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
            title = stringResource(R.string.location),
            icon = Icons.Filled.KeyboardArrowLeft,
            onIconClicked = onBackClicked,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        Text(
            text = locationDetails.name,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colors.primary)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LocationInfo(locationDetails = locationDetails)

        Spacer(modifier = Modifier.height(8.dp))

        LocationList(
            characters = locationDetails.characters,
            onCharacterClicked = onCharacterClicked,
        )
    }
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