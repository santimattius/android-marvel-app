package com.santimattius.marvel.composable.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    object Splash : NavigationItem(baseRoute = "splash")
    object Home : NavigationItem(baseRoute = "home")

    object CharacterDetail : NavigationItem(
        baseRoute = "character/detail",
        navArgs = listOf(NavArg.ItemId)
    ) {
        fun createRoute(itemId: Long) = "$baseRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId(key = "itemId", navType = NavType.IntType);
}