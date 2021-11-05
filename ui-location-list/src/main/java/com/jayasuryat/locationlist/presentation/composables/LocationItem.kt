package com.jayasuryat.locationlist.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jayasuryat.locationlist.domain.model.Location
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun LocationItem(
    location: Location,
    onLocationClicked: () -> Unit,
) {

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.large)
            .clickable { onLocationClicked() }
            .background(color = MaterialTheme.colors.primary)
            .padding(
                vertical = 16.dp,
                horizontal = 24.dp,
            ),
    ) {

        Surface(
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.background,
            shape = MaterialTheme.shapes.small,
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

@Preview(name = "Location item [light]")
@Preview(
    "Location item [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    val location = Location(
        id = 1,
        name = "Anatomy Park",
        type = "Micro verse",
        dimension = "Dimension C-137"
    )

    PreviewTheme {
        LocationItem(
            location = location,
            onLocationClicked = {},
        )
    }
}