package com.santimattius.marvel.composable.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.santimattius.marvel.composable.detail.presentation.DetailScreen
import com.santimattius.marvel.composable.home.presentation.HomeScreen
import com.santimattius.marvel.composable.splash.SplashScreen


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = NavigationItem.Splash.route,
    ) {
        navDefinitions(navController)
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
private fun NavGraphBuilder.navDefinitions(
    navController: NavController
) {
    composable(NavigationItem.Splash) {
        SplashScreen(navigate = {
            navController.popBackStack()
            navController.navigate(NavigationItem.Home.route)
        })
    }
    composable(NavigationItem.Home) {
        HomeScreen(
            onClick = { character ->
                navController.navigate(NavigationItem.CharacterDetail.createRoute(character.id))
            }
        )
    }
    composable(NavigationItem.CharacterDetail) {
        val id = it.findArg<Int>(NavArg.ItemId)
        DetailScreen(
            characterId = id,
            onUpClick = { navController.popBackStack() }
        )
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.composable(
    navItem: NavigationItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args,
        enterTransition = { _, _ ->
            // Let's make for a really long fade in
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = { _, _ ->
            fadeOut(animationSpec = tween(1000))
        }
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}