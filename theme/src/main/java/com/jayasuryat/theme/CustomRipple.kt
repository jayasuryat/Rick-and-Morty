package com.jayasuryat.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jayasuryat.theme.color.NeonGreen

internal object CustomRipple : RippleTheme {

    @Composable
    override fun defaultColor(): Color = if (isSystemInDarkTheme()) NeonGreen else Color.Black

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = MaterialTheme.colors.primary,
        lightTheme = isSystemInDarkTheme(),
    )
}