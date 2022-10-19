package com.ntn.findit.ui.screen.mainscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.screen.createchallenge.createChallengeNavGraph
import com.ntn.findit.ui.screen.ingame.thermometer.game
import com.ntn.findit.ui.screen.mainscreen.innerscreen.account.AccountInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge.ChallengeInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.chat.ChatInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.mychallenge.MyChallengeInnerScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(navController = navController, route = Graph.HOME ,startDestination = BottomNavigationBar.Challenges.route) {
        composable(BottomNavigationBar.Challenges.route) {
            ChallengeInnerScreen(navController)
        }
        composable(BottomNavigationBar.MyChallenges.route) {
            MyChallengeInnerScreen(navController)
        }
        composable(BottomNavigationBar.Chat.route) {
            ChatInnerScreen()
        }
        composable(BottomNavigationBar.Account.route) {
            AccountInnerScreen()
        }
        createChallengeNavGraph(navController)
        game(navController = navController)

    }
}