package com.jayasuryat.characterdetails.presentation.composable.character

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun InfoChip(
    title: String,
    value: String,
    subText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(32.dp))
            .background(color = MaterialTheme.colors.primary)
            .clickable { onClick() }
            .padding(16.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary.copy(0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Text(
            text = value,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Text(
            text = subText,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary.copy(0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}

@Preview(name = "Info chip [light]")
@Preview(
    name = "Info chip [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    PreviewTheme {
        InfoChip(
            title = "Title",
            value = "Value",
            subText = "Sub Text",
            onClick = {},
        )
    }
}