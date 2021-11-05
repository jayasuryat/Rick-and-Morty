package com.jayasuryat.sharedcomposable.composable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jayasuryat.themepreview.PreviewTheme


@Composable
public fun TopBar(
    title: String,
    icon: ImageVector,
    onIconClicked: () -> Unit,
) {

    Row(
        modifier = Modifier
            .wrapContentHeight()
    ) {

        Icon(
            modifier = Modifier
                .align(alignment = CenterVertically)
                .size(60.dp)
                .padding(end = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = onIconClicked,
                ),
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colors.onBackground,
        )

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


@Preview(name = "Top bar [light]")
@Preview(
    name = "Top bar [dark]",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {
    PreviewTheme {
        Column(
            modifier = Modifier.background(color = MaterialTheme.colors.background)
        ) {
            TopBar(
                title = "Rick and Morty",
                icon = Icons.Filled.KeyboardArrowLeft,
                onIconClicked = {},
            )
        }
    }
}
