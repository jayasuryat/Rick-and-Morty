package com.jayasuryat.episodedetails.presentation.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.EpisodeData


internal class EpisodeParameterProvider :
    PreviewParameterProvider<EpisodeData> {

    override val values: Sequence<EpisodeData>
        get() = sequenceOf(

            EpisodeData(
                episodeName = "Rick Potion #9",
                episode = "E6",
                season = "S1",
                episodeAiredOn = "January 27, 2014",
                characters = mutableListOf<Character>().apply {
                    repeat(10) {
                        add(Character(
                            id = it.toLong(),
                            name = "Rick sanchez $it",
                            image = ""
                        ))
                    }
                },
            )
        )
}