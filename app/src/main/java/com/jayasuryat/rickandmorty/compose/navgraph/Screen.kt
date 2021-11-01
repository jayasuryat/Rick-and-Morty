package com.jayasuryat.rickandmorty.compose.navgraph

sealed class Screen {

    abstract fun getRoute(): String

    object Home : Screen() {
        override fun getRoute(): String = "home"
    }

    object CharacterList : Screen() {
        override fun getRoute(): String = "characters"
    }

    object LocationsList : Screen() {
        override fun getRoute(): String = "locations"
    }
}