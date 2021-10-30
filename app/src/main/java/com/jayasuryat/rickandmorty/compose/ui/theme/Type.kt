package com.jayasuryat.rickandmorty.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.jayasuryat.rickandmorty.R

private val DmSans = FontFamily(
    Font(R.font.dm_sans_400_regular, FontWeight.W400),
    Font(R.font.dm_sans_500_medium, FontWeight.W500),
    Font(R.font.dm_sans_700_bold, FontWeight.W700)
)

val Typography = Typography(
    defaultFontFamily = DmSans
)