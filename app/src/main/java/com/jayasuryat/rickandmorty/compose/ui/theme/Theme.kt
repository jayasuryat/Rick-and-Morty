package com.jayasuryat.rickandmorty.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

    primary = Dune,
    primaryVariant = Dune,
    onPrimary = Color.White,

    secondary = NeonGreen,
    secondaryVariant = NeonGreen,
    onSecondary = Color.White,

    background = Color.Black,
    onBackground = Color.White,

    surface = Dune,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(

    primary = PaleGreen,
    primaryVariant = PaleGreen,
    onPrimary = Color.Black,

    secondary = Grass,
    secondaryVariant = Grass,
    onSecondary = Color.White,

    background = Milk,
    onBackground = Color.Black,

    surface = PaleGreen,
    onSurface = Color.Black,
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit,
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

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

private object CustomRipple : RippleTheme {

    @Composable
    override fun defaultColor(): Color = if (isSystemInDarkTheme()) NeonGreen else Color.Black

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = MaterialTheme.colors.primary,
        lightTheme = isSystemInDarkTheme(),
    )
}