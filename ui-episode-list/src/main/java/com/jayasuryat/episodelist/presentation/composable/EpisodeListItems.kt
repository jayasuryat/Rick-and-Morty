package com.jayasuryat.episodelist.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.episodelist.R
import com.jayasuryat.episodelist.presentation.EpisodeListData


@Composable
internal fun EpisodeListItemEpisode(
    episode: EpisodeListData.Episode,
    modifier: Modifier = Modifier,
    onEpisodeClicked: (episode: EpisodeListData.Episode) -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(percent = 100))
            .background(color = MaterialTheme.colors.primary)
            .clickable { onEpisodeClicked(episode) }
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {

        Text(
            text = episode.episodeNumber.toString(),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colors.background)
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = episode.episodeName,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
internal fun EpisodeListItemEpisodeSeason(
    season: EpisodeListData.Season,
    modifier: Modifier = Modifier,
    onSeasonClicked: (episode: EpisodeListData.Season) -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(percent = 100))
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(percent = 100)
            )
            .background(color = MaterialTheme.colors.primary)
            .clickable { onSeasonClicked(season) }
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),
    ) {

        Text(
            text = season.seasonName,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.icon_down),
            contentDescription = "Back",
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}


@Preview
@Composable
private fun Prev_Episode_List_Item(
    @PreviewParameter(EpisodesParameterProvider::class) episodes: List<EpisodeListData>,
) {

    val episode = episodes.filterIsInstance<EpisodeListData.Episode>()
        .firstOrNull() ?: return

    EpisodeListItemEpisode(
        episode = episode,
        onEpisodeClicked = {},
    )
}

@Preview
@Composable
private fun Prev_Episode_List_Season_Item(
    @PreviewParameter(EpisodesParameterProvider::class) episodes: List<EpisodeListData>,
) {

    val season = episodes.filterIsInstance<EpisodeListData.Season>()
        .firstOrNull() ?: return

    EpisodeListItemEpisodeSeason(
        season = season,
        onSeasonClicked = {},
    )
}
