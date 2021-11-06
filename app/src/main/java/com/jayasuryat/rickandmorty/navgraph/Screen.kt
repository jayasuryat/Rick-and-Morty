package com.jayasuryat.rickandmorty.navgraph

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

        fun getNavigableRoute(
            characterId: Long,
        ): String = "characterDetails/$characterId"
    }

    object CharacterEpisodes : Screen() {

        internal const val CHARACTER_ID: String = "characterId"

        override fun getRoute(): String = "characterEpisodes/{$CHARACTER_ID}"

        fun getNavigableRoute(
            characterId: Long,
        ): String = "characterEpisodes/$characterId"
    }

    object EpisodeList : Screen() {
        override fun getRoute(): String = "episodes"
    }

    object EpisodeDetails : Screen() {

        internal const val EPISODE_ID: String = "episodeId"

        override fun getRoute(): String = "episodeDetails/{$EPISODE_ID}"

        fun getNavigableRoute(
            episodeId: Long,
        ): String = "episodeDetails/$episodeId"
    }

    object LocationsList : Screen() {
        override fun getRoute(): String = "locations"
    }

    object LocationsDetails : Screen() {

        internal const val LOCATION_ID: String = "locationId"

        override fun getRoute(): String = "locationDetails/{$LOCATION_ID}"

        fun getNavigableRoute(
            locationId: Long,
        ): String = "locationDetails/$locationId"

    }
}