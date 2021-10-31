package com.jayasuryat.rickandmorty.compose.navgraph

sealed class Screen {

    abstract fun getRoute(): String

    object Home : Screen() {
        override fun getRoute(): String = "home"
    }

    object LocationsList : Screen() {
        override fun getRoute(): String = "locations"
    }
}