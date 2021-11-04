package com.jayasuryat.characterdetails.presentation.composable.character


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.presentation.character.CharacterDetailsViewModel
import com.jayasuryat.characterdetails.presentation.event.CharacterDetailsEvent
import com.jayasuryat.characterdetails.presentation.event.CharacterDetailsEvent.*
import com.jayasuryat.event.EventListener


@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel,
    eventListener: EventListener<CharacterDetailsEvent>,
) {

    val character by viewModel.obsCharacter.observeAsState()

    val char = character ?: return

    CharacterDetailsBody(
        character = char,
        onBackClicked = { eventListener.onEvent(OnBackClicked) },
        onLocationClicked = { location ->
            val locationId = location.id
                .takeIf { !it.isNullOrEmpty() }?.toLong()
                ?: return@CharacterDetailsBody
            eventListener.onEvent(OpenLocation(locationId = locationId))
        },
        onOriginClicked = { location ->
            val locationId = location.id
                .takeIf { !it.isNullOrEmpty() }?.toLong()
                ?: return@CharacterDetailsBody
            eventListener.onEvent(OpenLocation(locationId = locationId))
        },
        onEpisodesClicked = {
            eventListener.onEvent(OpenCharacterEpisodes(characterId = char.id.toLong()))
        },
    )
}

@Preview
@Composable
private fun Prev_Screen(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterDetailsBody(
        character = character,
        onBackClicked = {},
        onLocationClicked = {},
        onOriginClicked = {},
        onEpisodesClicked = {},
    )
}
