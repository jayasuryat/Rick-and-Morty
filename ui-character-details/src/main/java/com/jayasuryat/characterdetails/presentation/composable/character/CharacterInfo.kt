package com.jayasuryat.characterdetails.presentation.composable.character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.characterdetails.R
import com.jayasuryat.characterdetails.domain.models.CharacterDetails


@Composable
internal fun CharacterInfo(
    character: CharacterDetails,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(32.dp))
            .background(color = MaterialTheme.colors.primary)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(32.dp),
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

        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.icon_alien),
            contentDescription = "Species type ${character.species.value}",
            tint = MaterialTheme.colors.background,
        )

        Spacer(modifier = Modifier.width(32.dp))

        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.icon_alien),
            contentDescription = "Species type ${character.species.value}",
            tint = MaterialTheme.colors.background,
        )

        Spacer(modifier = Modifier.width(32.dp))

        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.icon_gender_neutral),
            contentDescription = "Species type ${character.species.value}",
            tint = MaterialTheme.colors.background,
        )
    }
}

@Preview
@Composable
private fun PrevCharacter_Info(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterInfo(
        character = character,
    )
}

@Preview
@Composable
private fun PrevCharacter_Info_Top(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    Column {
        CharacterInfoTop(
            character = character,
        )
    }
}

@Preview
@Composable
private fun PrevCharacter_Info_Bottom(
    @PreviewParameter(CharacterParameterProvider::class) character: CharacterDetails,
) {

    CharacterInfoBottom(
        character = character,
    )
}