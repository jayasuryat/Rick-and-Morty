package com.jayasuryat.home.composable

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jayasuryat.home.R
import com.jayasuryat.themepreview.PreviewTheme

@Composable
internal fun HomeItem(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(MaterialTheme.shapes.large)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.large,
            )
            .clickable { onClick() }
            .background(color = MaterialTheme.colors.primary),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onPrimary,
            text = title,
        )

        Image(
            modifier = Modifier
                .padding(
                    top = 64.dp,
                    end = 16.dp,
                )
                .wrapContentSize(),
            painter = painterResource(id = image),
            contentDescription = "$title Image",
        )
    }
}

@Preview(name = "Home item [light]")
@Preview(
    name = "Home item [dark]",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {

    PreviewTheme {

        HomeItem(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            title = "Characters",
            image = R.drawable.img_rick,
            onClick = { },
        )
    }
}