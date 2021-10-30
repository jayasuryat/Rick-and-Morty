package com.jayasuryat.rickandmorty.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jayasuryat.base.event.noOpEventListener
import com.jayasuryat.home.Home
import com.jayasuryat.rickandmorty.compose.ui.theme.RickAndMortyTheme

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Home(noOpEventListener())
            }
        }
    }
}