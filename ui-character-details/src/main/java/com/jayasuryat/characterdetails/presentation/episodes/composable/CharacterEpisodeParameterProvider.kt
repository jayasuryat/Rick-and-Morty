package com.jayasuryat.characterdetails.presentation.episodes.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jayasuryat.characterdetails.presentation.episodes.CharacterEpisodeData


internal class CharacterEpisodeParameterProvider :
    PreviewParameterProvider<List<CharacterEpisodeData>> {

    override val values: Sequence<List<CharacterEpisodeData>>
        get() = sequenceOf(

            listOf(
                CharacterEpisodeData.EpisodeData(
                    episodeId = 1,
                    episodeName = "First episode",
                    season = 1,
                    episode = 1,
                ),

                CharacterEpisodeData.SeasonDivider(1),

                CharacterEpisodeData.EpisodeData(
                    episodeId = 2,
                    episodeName = "Second episode",
                    season = 2,
                    episode = 1,
                ),
            )
        )
}