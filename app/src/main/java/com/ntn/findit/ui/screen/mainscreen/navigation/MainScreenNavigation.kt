package com.ntn.findit.ui.screen.mainscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.navigation.AppScreens
import com.ntn.findit.ui.screen.mainscreen.innerscreen.account.AccountInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.challenge.ChallengeInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.chat.ChatInnerScreen
import com.ntn.findit.ui.screen.mainscreen.innerscreen.mychallenge.MyChallengeInnerScreen

@Composable
fun MainScreenNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationBar.Challenges.screen_route) {
        composable(BottomNavigationBar.Challenges.screen_route) {
            ChallengeInnerScreen(navController)
        }
        composable(BottomNavigationBar.MyChallenges.screen_route) {
            MyChallengeInnerScreen()
        }
        composable(BottomNavigationBar.Chat.screen_route) {
            ChatInnerScreen()
        }
        composable(BottomNavigationBar.Account.screen_route) {
            AccountInnerScreen()
        }

    }
}