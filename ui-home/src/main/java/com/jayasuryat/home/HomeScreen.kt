package com.jayasuryat.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class HomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                CompositionLocalProvider(
                    LocalRippleTheme provides CustomRipple,
                    content = {
                        Home()
                    }
                )
            }
        }
    }

    private object CustomRipple : RippleTheme {

        @Composable
        override fun defaultColor(): Color = colorResource(id = R.color.neon_green)

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
            contentColor = colorResource(id = R.color.black),
            lightTheme = false,
        )
    }

    @Composable
    private fun Home() {

        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.black))
                .padding(
                    horizontal = 24.dp,
                    vertical = 12.dp,
                )
        ) {
            HomeItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                title = "Characters",
                image = R.drawable.img_rick,
                onClick = {},
            )
            HomeItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                title = "Episodes",
                image = R.drawable.img_episode,
                onClick = {},
            )
            HomeItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                title = "Locations",
                image = R.drawable.img_location,
                onClick = {},
            )
        }
    }

    @Composable
    private fun HomeItem(
        modifier: Modifier = Modifier,
        title: String,
        @DrawableRes image: Int,
        onClick: () -> Unit,
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.neon_green),
                    shape = RoundedCornerShape(24.dp),
                )
                .clickable { onClick() }
                .background(color = Color(0xFF181818)),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(24.dp),
                fontSize = 32.sp,
                color = Color.White,
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

    @Preview
    @Composable
    private fun Preview() {
        Home()
    }
}