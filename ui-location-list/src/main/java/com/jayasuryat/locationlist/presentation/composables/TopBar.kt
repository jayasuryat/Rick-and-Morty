package com.jayasuryat.locationlist.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.locationlist.R

@Composable
internal fun TopBar(
    title: String,
    onBackPressed: () -> Unit,
) {

    Row(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        IconButton(
            modifier = Modifier
                .align(alignment = CenterVertically)
                .size(60.dp),
            onClick = { onBackPressed() },
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = title,
                tint = MaterialTheme.colors.onBackground,
            )
        }

        Text(
            text = stringResource(id = R.string.locations),
            style = MaterialTheme.typography.h4.copy(fontSize = 32.sp),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = CenterVertically),
        )
    }
}

@Preview
@Composable
private fun Prev_Top_Bar() {
    TopBar(
        title = stringResource(id = R.string.locations)
    ) {}
}