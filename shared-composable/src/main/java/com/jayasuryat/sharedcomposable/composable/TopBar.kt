package com.jayasuryat.sharedcomposable.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun TopBar(
    title: String,
    @DrawableRes icon: Int,
    onIconClicked: () -> Unit,
) {

    Row(
        modifier = Modifier
            .wrapContentHeight()
    ) {

        IconButton(
            modifier = Modifier
                .align(alignment = CenterVertically)
                .size(60.dp),
            onClick = { onIconClicked() },
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = MaterialTheme.colors.onBackground,
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h4.copy(fontSize = 32.sp),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = CenterVertically),
        )
    }
}

// TODO: 31/10/21 Need to add preview
/*
@Preview
@Composable
private fun Prev_Top_Bar() {
    // Not added due to unavailability of icon resource
}
*/
