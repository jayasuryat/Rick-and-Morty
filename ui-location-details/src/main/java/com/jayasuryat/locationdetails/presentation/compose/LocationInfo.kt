package com.jayasuryat.locationdetails.presentation.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.themepreview.PreviewTheme

@Composable
fun LocationInfo(
    locationDetails: LocationDetails,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colors.primary)
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp,
            )
    ) {

        Text(
            text = locationDetails.type,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .wrapContentSize()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(16.dp)
                .background(color = MaterialTheme.colors.onPrimary)
                .align(Alignment.CenterVertically),
        )

        Text(
            text = locationDetails.dimension,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .wrapContentSize()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview(name = "Location info [light]")
@Preview(
    name = "Location info [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    PreviewTheme {
        LocationInfo(
            locationDetails = locationDetails,
        )
    }
}