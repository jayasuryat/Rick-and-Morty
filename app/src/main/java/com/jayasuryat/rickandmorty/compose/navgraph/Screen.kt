package com.jayasuryat.rickandmorty.compose.navgraph

sealed class Screen {

    abstract fun getRoute(): String

    object Home : Screen() {
        override fun getRoute(): String = "home"
    }

    object CharacterList : Screen() {
        override fun getRoute(): String = "characters"
    }

    object CharacterDetails : Screen() {

        internal const val CHARACTER_ID: String = "characterId"

        override fun getRoute(): String = "characterDetails/{$CHARACTER_ID}"

        fun getNavigableRoute(id: Long): String = "characterDetails/$id"
    }

    object CharacterEpisodes : Screen() {

        internal const val CHARACTER_ID: String = "characterId"

        override fun getRoute(): String = "characterEpisodes/{$CHARACTER_ID}"

        fun getNavigableRoute(id: Long): String = "characterEpisodes/$id"
    }

    object EpisodeList : Screen() {
        override fun getRoute(): String = "episodes"
    }

    object LocationsList : Screen() {
        override fun getRoute(): String = "locations"
    }
}