package com.jayasuryat.rickandmorty.compose.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jayasuryat.characterlist.presentation.composable.CharacterList
import com.jayasuryat.characterlist.presentation.event.CharacterListEvent
import com.jayasuryat.home.composable.Home
import com.jayasuryat.home.event.HomeEvent
import com.jayasuryat.locationlist.presentation.composables.LocationList
import com.jayasuryat.locationlist.presentation.event.LocationListEvent

@Composable
fun RickAndMortyApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.getRoute(),
    ) {

        composable(Screen.Home.getRoute()) {
            Home { event ->
                when (event) {
                    HomeEvent.OpenCharacters -> navController.navigate(Screen.CharacterList.getRoute())
                    HomeEvent.OpenEpisodes -> TODO()
                    HomeEvent.OpenLocations -> navController.navigate(Screen.LocationsList.getRoute())
                }
            }
        }

        composable(Screen.LocationsList.getRoute()) {
            LocationList { event ->
                when (event) {
                    is LocationListEvent.OnBackPressed -> navController.popBackStack()
                    is LocationListEvent.OpenLocation -> TODO()
                }
            }
        }

        composable(Screen.CharacterList.getRoute()) {
            CharacterList { event ->
                when (event) {
                    is CharacterListEvent.OnBackPressed -> navController.popBackStack()
                    is CharacterListEvent.OpenCharacter -> TODO()
                }
            }
        }
    }
}