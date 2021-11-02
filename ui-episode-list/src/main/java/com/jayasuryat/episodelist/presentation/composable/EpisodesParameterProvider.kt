package com.jayasuryat.episodelist.presentation.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jayasuryat.episodelist.presentation.EpisodeListData


internal class EpisodesParameterProvider :
    PreviewParameterProvider<List<EpisodeListData>> {

    override val values: Sequence<List<EpisodeListData>>
        get() = sequenceOf(

            listOf(

                EpisodeListData.Season(
                    seasonName = "Season 1",
                    isExpanded = true,
                ),

                EpisodeListData.Episode(
                    episodeId = 1,
                    seasonName = "1",
                    episodeName = "Episode name 1",
                    episodeNumber = 1,
                ),

                EpisodeListData.Episode(
                    episodeId = 2,
                    seasonName = "1",
                    episodeName = "Episode name 2",
                    episodeNumber = 2,
                ),

                EpisodeListData.Season(
                    seasonName = "Season 2",
                    isExpanded = true,
                ),

                EpisodeListData.Episode(
                    episodeId = 3,
                    seasonName = "2",
                    episodeName = "Episode name 3",
                    episodeNumber = 3,
                ),
            )
        )
}