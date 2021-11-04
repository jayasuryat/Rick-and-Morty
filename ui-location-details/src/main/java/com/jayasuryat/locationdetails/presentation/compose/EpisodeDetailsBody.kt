package com.jayasuryat.locationdetails.presentation.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jayasuryat.locationdetails.R
import com.jayasuryat.locationdetails.domain.models.Character
import com.jayasuryat.locationdetails.domain.models.LocationDetails
import com.jayasuryat.sharedcomposable.composable.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LocationDetailsBody(
    locationDetails: LocationDetails,
    onBackClicked: () -> Unit,
    onCharacterClicked: (character: Character) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(
                top = 16.dp,
                start = 24.dp,
                end = 24.dp,
            ),
    ) {

        TopBar(
            title = stringResource(R.string.location),
            icon = R.drawable.icon_back,
            onIconClicked = onBackClicked,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        Text(
            text = locationDetails.name,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(percent = 100))
                .background(color = MaterialTheme.colors.primary)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(percent = 100))
                .background(color = MaterialTheme.colors.primary)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp,
                )
        ) {

            Text(
                text = locationDetails.type,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .align(CenterVertically)
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(16.dp)
                    .background(color = MaterialTheme.colors.onPrimary)
                    .align(CenterVertically),
            )

            Text(
                text = locationDetails.dimension,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .align(CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(32.dp))
                .background(color = MaterialTheme.colors.primary)
                .padding(16.dp),
        ) {

            Text(
                text = stringResource(id = R.string.characters),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.align(alignment = CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
            ) {

                items(
                    items = locationDetails.characters,
                ) { character ->

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { onCharacterClicked(character) }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(32.dp),
                            )
                    ) {

                        Image(
                            painter = rememberImagePainter(
                                data = character.image,
                                builder = {
                                    crossfade(true)
                                    placeholder(R.drawable.drawable_left)
                                }
                            ),
                            contentDescription = "${character.name} image",
                        )

                        Spacer(modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.background.copy(alpha = 0.6f))
                        )

                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.caption,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp)
                                .align(alignment = Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev_Location_Details(
    @PreviewParameter(LocationParameterProvider::class) locationDetails: LocationDetails,
) {

    LocationDetailsBody(
        locationDetails = locationDetails,
        onBackClicked = {},
        onCharacterClicked = {},
    )
}