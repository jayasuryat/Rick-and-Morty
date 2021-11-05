package com.jayasuryat.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.jayasuryat.theme.color.DarkColorPalette
import com.jayasuryat.theme.color.LightColorPalette
import com.jayasuryat.theme.shape.Shapes
import com.jayasuryat.theme.type.Typography


@Composable
public fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            CompositionLocalProvider(
                LocalRippleTheme provides CustomRipple,
                content = content
            )
        }
    )
}