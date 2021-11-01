package com.jayasuryat.rickandmorty.compose.navgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jayasuryat.characterdetails.presentation.character.CharacterDetailsViewModel
import com.jayasuryat.characterdetails.presentation.composable.character.CharacterDetails
import com.jayasuryat.characterdetails.presentation.event.CharacterDetailsEvent
import com.jayasuryat.characterlist.presentation.composable.CharacterList
import com.jayasuryat.characterlist.presentation.event.CharacterListEvent
import com.jayasuryat.event.Event
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

        composable(
            route = Screen.Home.getRoute(),
        ) {
            Home { event ->
                when (event) {
                    HomeEvent.OpenCharacters -> navController.navigate(Screen.CharacterList.getRoute())
                    HomeEvent.OpenEpisodes -> pendingNavigationImpl(event)
                    HomeEvent.OpenLocations -> navController.navigate(Screen.LocationsList.getRoute())
                }
            }
        }

        composable(
            route = Screen.CharacterList.getRoute(),
        ) {
            CharacterList { event ->
                when (event) {
                    is CharacterListEvent.OnBackPressed -> navController.popBackStack()
                    is CharacterListEvent.OpenCharacter -> {
                        val route = Screen.CharacterDetails.getNavigableRoute(event.characterId)
                        navController.navigate(route)
                    }
                }
            }
        }

        composable(
            route = Screen.CharacterDetails.getRoute(),
            arguments = listOf(
                navArgument(Screen.CharacterDetails.CHARACTER_ID) { type = NavType.LongType }
            )
        ) {

            val characterId = it.arguments
                ?.getLong(Screen.CharacterDetails.CHARACTER_ID)
                ?: throw IllegalArgumentException("Character id cannot be null")

            val viewModel: CharacterDetailsViewModel = hiltViewModel()
            viewModel.getCharacterDetails(characterId)

            CharacterDetails(
                viewModel = viewModel,
            ) { event ->
                when (event) {
                    is CharacterDetailsEvent.OnBackPressed -> navController.popBackStack()
                    is CharacterDetailsEvent.OpenCharacterEpisodes -> pendingNavigationImpl(event)
                    is CharacterDetailsEvent.OpenLocation -> pendingNavigationImpl(event)
                }
            }
        }

        composable(
            route = Screen.LocationsList.getRoute(),
        ) {
            LocationList { event ->
                when (event) {
                    is LocationListEvent.OnBackPressed -> navController.popBackStack()
                    is LocationListEvent.OpenLocation -> pendingNavigationImpl(event)
                }
            }
        }
    }
}

private fun pendingNavigationImpl(event: Event) {
    Log.e("RickAndMorty", "Navigation impl pending for event : ${event::class.java}")
}