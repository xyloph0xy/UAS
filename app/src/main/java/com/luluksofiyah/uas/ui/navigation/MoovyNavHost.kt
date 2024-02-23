package com.luluksofiyah.uas.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luluksofiyah.uas.ui.screens.detail.DetailScreen
import com.luluksofiyah.uas.ui.screens.favorite.FavoriteScreen
import com.luluksofiyah.uas.ui.screens.home.HomeScreen

@Composable
fun MoovyNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = MoovyScreen.HOME.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeComposable(navController = navController)
        detailComposable(navController = navController)
        favoriteComposable(navController = navController)
    }
}

fun NavGraphBuilder.homeComposable(navController: NavHostController) {
    composable(
        route = MoovyScreen.HOME.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        content = {
            HomeScreen(
                navController = navController,
            )
        }
    )
}

fun NavGraphBuilder.detailComposable(navController: NavHostController) {
    composable(
        route = "${MoovyScreen.DETAIL.route}/{movieId}",
        arguments = listOf(
            navArgument(name = "movieId") {
                type = NavType.IntType
            }
        ),
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        content = {
            val movieId = navController.currentBackStackEntry?.arguments?.getInt("movieId")
            DetailScreen(movieId = movieId)
        }
    )
}

fun NavGraphBuilder.favoriteComposable(navController: NavHostController) {
    composable(
        route = MoovyScreen.FAVORITE.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        content = {
            FavoriteScreen(
                navController = navController,
            )
        }
    )
}