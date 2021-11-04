package com.jayasuryat.locationdetails.presentation.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails


internal class LocationParameterProvider :
    PreviewParameterProvider<LocationDetails> {

    override val values: Sequence<LocationDetails>
        get() = sequenceOf(

            LocationDetails(
                id = 1,
                name = "Immortality Field Resort",
                type = "Resort",
                dimension = "unknown",
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