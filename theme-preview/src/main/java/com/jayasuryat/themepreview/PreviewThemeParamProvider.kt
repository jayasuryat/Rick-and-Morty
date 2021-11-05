package com.jayasuryat.themepreview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider


public open class PreviewThemeParamProvider : PreviewParameterProvider<PreviewThemeProvider> {

    override val values: Sequence<PreviewThemeProvider>
        get() = sequenceOf(
            PreviewThemeProvider(true),
            PreviewThemeProvider(false)
        )
}
