package com.jayasuryat.characterdetails.presentation.composable.character

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.Location
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterDetailsBody(
    character: CharacterDetails,
    onBackClicked: () -> Unit,
    onLocationClicked: (location: Location) -> Unit,
    onOriginClicked: (location: Location) -> Unit,
    onEpisodesClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            val (imageView, infoView) = createRefs()

            CharacterImageWithBackButton(
                character = character,
                modifier = Modifier
                    .constrainAs(imageView) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onBackClicked = onBackClicked,
            )

            CharacterInfo(
                character = character,
                modifier = Modifier
                    .constrainAs(infoView) {
                        top.linkTo(imageView.bottom)
                        bottom.linkTo(imageView.bottom)
                    }
                    .padding(horizontal = 24.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        InfoChip(
            title = stringResource(id = R.string.location),
            value = character.location?.name ?: "<Location Unknown>",
            subText = character.location?.dimension ?: "<Dimension Unknown>",
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = { character.location?.let(onLocationClicked) },
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        InfoChip(
            title = stringResource(id = R.string.origin),
            value = character.origin?.name ?: "<Location Unknown>",
            subText = character.origin?.dimension ?: "<Dimension Unknown>",
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = { character.location?.let(onOriginClicked) },
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.episodes),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(32.dp))
                .background(color = MaterialTheme.colors.primary)
                .clickable { onEpisodesClicked() }
                .padding(16.dp)
        )
    }
}

@Preview(name = "Character details screen [light]")
@Preview(
    name = "Character details screen [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    PreviewTheme {
        CharacterDetailsBody(
            character = character,
            onBackClicked = {},
            onLocationClicked = {},
            onOriginClicked = {},
            onEpisodesClicked = {},
        )
    }
}