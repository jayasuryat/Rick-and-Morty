package com.jayasuryat.themepreview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.jayasuryat.theme.RickAndMortyTheme


@Composable
public fun PreviewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    RickAndMortyTheme(
        darkTheme = darkTheme,
        content = content
    )
}