package com.jayasuryat.episodedetails.presentation.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.jayasuryat.episodedetails.R
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.EpisodeData
import com.jayasuryat.sharedcomposable.composable.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun EpisodeDetailsBody(
    episodeData: EpisodeData,
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
            title = stringResource(R.string.episode),
            icon = R.drawable.icon_back,
            onIconClicked = onBackClicked,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
        )

        Text(
            text = episodeData.episodeName,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colors.primary)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colors.primary)
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp,
                )
        ) {

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .align(alignment = CenterVertically),
            ) {

                Text(
                    text = episodeData.episode,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .clip(shape = MaterialTheme.shapes.small)
                        .background(color = MaterialTheme.colors.background)
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = episodeData.season,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                        .clip(shape = MaterialTheme.shapes.small)
                        .background(color = MaterialTheme.colors.background)
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(alignment = CenterVertically),
            ) {

                Text(
                    text = stringResource(id = R.string.aired_on),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.align(alignment = CenterHorizontally),
                )

                Text(
                    text = episodeData.episodeAiredOn,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialTheme.shapes.large)
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
                    items = episodeData.characters,
                ) { character ->

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .clip(shape = MaterialTheme.shapes.large)
                            .clickable { onCharacterClicked(character) }
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colors.secondary,
                                shape = MaterialTheme.shapes.large,
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
private fun Prev_Episode_Details(
    @PreviewParameter(EpisodeParameterProvider::class) episodeData: EpisodeData,
) {

    EpisodeDetailsBody(
        episodeData = episodeData,
        onBackClicked = {},
        onCharacterClicked = {},
    )
}