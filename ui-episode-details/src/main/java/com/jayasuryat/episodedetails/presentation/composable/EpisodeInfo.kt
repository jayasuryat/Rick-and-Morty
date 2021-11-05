package com.jayasuryat.episodedetails.presentation.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.episodedetails.R
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun EpisodeInfo(
    episodeData: EpisodeData,
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

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .align(alignment = Alignment.CenterVertically),
        ) {

            Text(
                text = episodeData.episode,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = MaterialTheme.colors.background)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = episodeData.season,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = MaterialTheme.colors.background)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.CenterVertically),
        ) {

            Text(
                text = stringResource(id = R.string.aired_on),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            )

            Text(
                text = episodeData.episodeAiredOn,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            )
        }
    }
}

@Preview(name = "Episode info [light]")
@Preview(
    "Episode info [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {
    PreviewTheme {
        EpisodeInfo(episodeData = episodeData)
    }
}