package com.jayasuryat.characterdetails.presentation.composable.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData

@Composable
internal fun CharacterEpisodeItem(
    episode: CharacterEpisodeData.EpisodeData,
    modifier: Modifier = Modifier,
    onEpisodeClicked: (episode: CharacterEpisodeData.EpisodeData) -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colors.primary)
            .clickable { onEpisodeClicked(episode) }
            .padding(12.dp),
    ) {

        val seasonNumber = "S" + episode.season

        Text(
            text = seasonNumber,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colors.background)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        val episodeNumber = "E" + episode.season

        Text(
            text = episodeNumber,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colors.background)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = episode.episodeName,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
internal fun CharacterSeasonDivider(
    modifier: Modifier = Modifier,
) {

    Divider(
        modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colors.secondary)
    )
}

@Preview
@Composable
private fun Prev_Character_Episode_Item(
    @PreviewParameter(CharacterEpisodeParameterProvider::class) episodes: List<CharacterEpisodeData>,
) {

    val episode = episodes.filterIsInstance<CharacterEpisodeData.EpisodeData>()
        .firstOrNull() ?: return

    CharacterEpisodeItem(
        episode = episode,
        onEpisodeClicked = {},
    )
}

@Preview
@Composable
private fun Prev_Character_Season_Divider() {

    CharacterSeasonDivider()
}
