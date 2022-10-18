package com.ntn.findit.ui.screen.createchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.createchallenge.addclue.AddClueCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.basicinfo.BasicInfoCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.challengepreview.CreateChallengePreviewScreen
import com.ntn.findit.ui.screen.createchallenge.createclues.CluesCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeScreen
import com.ntn.findit.ui.screen.mainscreen.navigation.BottomNavigationBar

@Composable
fun CreateChallenge() {
    val navController = rememberNavController()
    MainScreenNavigationGraph(navController)
}

@Composable
fun MainScreenNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = CreateChallengeGraph.BasicInfo.route) {
        composable(CreateChallengeGraph.BasicInfo.route){
            BasicInfoCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallengeGraph.ChallengeLocation.route){
            LocationCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallengeGraph.CreateClues.route){
            CluesCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallengeGraph.AddClue.route){
            AddClueCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallengeGraph.ChallengePreview.route){
            CreateChallengePreviewScreen(navController = navController)
        }

    }
}