package com.jayasuryat.characterdetails.presentation.composable.character


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.presentation.character.CharacterDetailsViewModel
import com.jayasuryat.characterdetails.presentation.event.CharacterDetailsEvent
import com.jayasuryat.characterdetails.presentation.event.CharacterDetailsEvent.OnBackPressed
import com.jayasuryat.event.EventListener


@Composable
fun CharacterDetails(
    viewModel: CharacterDetailsViewModel,
    eventListener: EventListener<CharacterDetailsEvent>,
) {

    val character by viewModel.obsCharacter.observeAsState()

    val char = character ?: return

    CharacterDetailsBody(
        character = char,
        onBackPressed = { eventListener.onEvent(OnBackPressed) },
        onLocationClicked = {},
        onOriginClicked = {},
    )
}

@Preview
@Composable
private fun Prev_Screen(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterDetailsBody(
        character = character,
        onBackPressed = {},
        onLocationClicked = {},
        onOriginClicked = {},
    )
}
