package com.santimattius.marvel.composable.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.santimattius.marvel.composable.detail.presentation.CharacterDetailScreen
import com.santimattius.marvel.composable.home.presentation.HomeScreen


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Characters.route
    ) {
        charactersNav(navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.charactersNav(
    navController: NavController
) {
    composable(NavigationItem.Characters) {
        HomeScreen(
            onClick = { character ->
                navController.navigate(NavigationItem.CharacterDetail.createRoute(character.id))
            }
        )
    }

    composable(NavigationItem.CharacterDetail) {
        val id = it.findArg<Int>(NavArg.ItemId)
        CharacterDetailScreen(
            characterId = id,
            onUpClick = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavigationItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}