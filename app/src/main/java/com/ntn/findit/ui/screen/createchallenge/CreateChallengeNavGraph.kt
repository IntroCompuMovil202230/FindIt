package com.ntn.findit.ui.screen.createchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ntn.findit.ui.navigation.Graph
import com.ntn.findit.ui.screen.createchallenge.addclue.AddClueCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.basicinfo.BasicInfoCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.challengepreview.CreateChallengePreviewScreen
import com.ntn.findit.ui.screen.createchallenge.createclues.CluesCreateChallengeScreen
import com.ntn.findit.ui.screen.createchallenge.location.LocationCreateChallengeScreen


fun NavGraphBuilder.createChallengeNavGraph(navController: NavHostController) {
    navigation(route = Graph.CREATE_CHALLENGE, startDestination = CreateChallenge.BasicInfo.route) {
        composable(CreateChallenge.BasicInfo.route){
            BasicInfoCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallenge.ChallengeLocation.route){
            LocationCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallenge.CreateClues.route){
            CluesCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallenge.AddClue.route){
            AddClueCreateChallengeScreen(navController = navController)
        }
        composable(CreateChallenge.ChallengePreview.route){
            CreateChallengePreviewScreen(navController = navController)
        }
    }
}


sealed class CreateChallenge(val route: String){
    object BasicInfo: CreateChallenge("basic_info")
    object ChallengeLocation: CreateChallenge("challenge_location")
    object CreateClues: CreateChallenge("create_clues")
    object AddClue: CreateChallenge("add_clue")
    object ChallengePreview: CreateChallenge("create_challenge")
}
