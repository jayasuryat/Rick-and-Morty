package com.jayasuryat.characterdetails.presentation.composable.character

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Gender.*
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Species
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Status.Alive
import com.jayasuryat.characterdetails.domain.models.CharacterDetails.Status.Dead
import com.jayasuryat.themepreview.PreviewTheme


@Composable
internal fun CharacterInfo(
    character: CharacterDetails,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colors.primary)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.large,
            )
            .padding(16.dp)
    ) {

        CharacterInfoTop(
            character = character,
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))

        Divider(
            thickness = 1.dp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))

        CharacterInfoBottom(character = character,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun CharacterInfoTop(
    character: CharacterDetails,
) {

    Text(
        text = character.name,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary,
        style = MaterialTheme.typography.h4.copy(fontSize = 32.sp),
    )

    val type = character.type
        .takeIf { !it.isNullOrEmpty() }
        ?: "Undefined"

    Text(
        text = "($type)",
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
        style = MaterialTheme.typography.body2,
    )
}

@Composable
private fun CharacterInfoBottom(
    character: CharacterDetails,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .wrapContentWidth(),
    ) {

        @IdRes val speciesImageId = when (character.species) {
            Species.Alien -> R.drawable.icon_alien
            Species.Human -> R.drawable.icon_user
            Species.Humanoid -> R.drawable.icon_robo
            Species.Other -> R.drawable.icon_paw
        }

        @IdRes val genderImageId = when (character.gender) {
            Female -> R.drawable.icon_gender_female
            Male -> R.drawable.icon_gender_male
            Genderless -> R.drawable.icon_gender_neutral
            Unknown -> R.drawable.icon_gender_neutral // TODO Need to add a diff icon
        }

        @IdRes val statusColorId = when (character.status) {
            Alive -> R.color.green
            Dead -> R.color.red
            CharacterDetails.Status.Unknown -> R.color.grey
        }

        InfoIcon(
            value = character.species.value,
            icon = speciesImageId,
        )

        Spacer(modifier = Modifier.width(8.dp))

        StatusIcon(
            value = character.status.value,
            statusColor = colorResource(statusColorId),
        )

        Spacer(modifier = Modifier.width(8.dp))

        InfoIcon(
            value = character.gender.value,
            icon = genderImageId,
        )
    }
}

@Composable
private fun InfoIcon(
    value: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .wrapContentSize()
            .width(60.dp),
    ) {

        Icon(
            modifier = Modifier
                .size(30.dp)
                .align(CenterHorizontally),
            painter = painterResource(id = icon),
            contentDescription = "$value icon",
            tint = MaterialTheme.colors.onPrimary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentSize()
                .align(CenterHorizontally),
            color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
        )
    }
}

@Composable
private fun StatusIcon(
    value: String,
    statusColor: Color,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .wrapContentSize()
            .width(60.dp),
    ) {

        Surface(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .align(CenterHorizontally),
            color = statusColor
        ) {}

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentSize()
                .align(CenterHorizontally),
            color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
        )
    }
}


@Preview(name = "Character info [light]")
@Preview(
    name = "Character info [dark]",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PrevCharacter_Info(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    PreviewTheme {
        CharacterInfo(
            character = character,
        )
    }
}