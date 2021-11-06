package com.jayasuryat.characterdetails.presentation.character.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Location


internal class CharacterParameterProvider :
    PreviewParameterProvider<CharacterDetails> {

    override val values: Sequence<CharacterDetails>
        get() = sequenceOf(
            CharacterDetails(
                id = 1.toString(),
                name = "Rick Sanchez",
                image = "",
                status = CharacterDetails.Status.enumFrom("Alive"),
                species = CharacterDetails.Species.enumFrom("Human"),
                type = "Mad Scientist",
                gender = CharacterDetails.Gender.enumFrom("Male"),
                location = Location(
                    id = "20",
                    name = "Earth (Replacement Dimension)",
                    type = "Planet",
                    dimension = "Replacement Dimension",
                ),
                origin = Location(
                    id = "20",
                    name = "Earth (Replacement Dimension)",
                    type = "Planet",
                    dimension = "Replacement Dimension",
                ),
            )
        )
}