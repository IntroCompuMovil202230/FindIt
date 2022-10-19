package com.ntn.findit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ntn.findit.ui.screen.createchallenge.CreateChallenge
import com.ntn.findit.ui.screen.ingame.thermometer.Game
import com.ntn.findit.ui.screen.mainscreen.MainScreenView

@Composable
fun RootNavigationGraph(navController: NavHostController, start: String) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = start
    ) {
        authNavGraph(navController)
        composable(route = Graph.HOME){
            MainScreenView()
        }
        composable(route = Graph.GAME){
            Game()
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val CREATE_CHALLENGE = "create_challenge_graph"
    const val GAME = "game_graph"
}