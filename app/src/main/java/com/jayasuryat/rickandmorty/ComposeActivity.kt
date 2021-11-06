package com.jayasuryat.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jayasuryat.theme.RickAndMortyTheme
import com.jayasuryat.rickandmorty.navgraph.RickAndMortyApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                RickAndMortyApp()
            }
        }
    }
}