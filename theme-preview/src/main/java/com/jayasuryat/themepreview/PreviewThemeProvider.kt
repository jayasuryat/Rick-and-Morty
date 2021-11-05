package com.jayasuryat.themepreview

import androidx.compose.runtime.Composable
import com.jayasuryat.theme.RickAndMortyTheme

public open class PreviewThemeProvider(
    private val isDarkTheme: Boolean,
) {

    @Composable
    public fun Theme(
        content: @Composable () -> Unit,
    ) {
        RickAndMortyTheme(
            darkTheme = isDarkTheme,
            content = content
        )
    }
}