package com.ntn.findit.ui.screen.ingame.thermometer

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ntn.findit.ui.navigation.Graph

fun NavGraphBuilder.game(navController: NavHostController) {
    navigation(route = Graph.GAME, startDestination = "game") {
        composable("game"){
            Game()
        }
    }
}