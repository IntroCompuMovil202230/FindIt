package com.ntn.findit.ui.screen.createchallenge

sealed class CreateChallengeGraph(val route: String){
    object BasicInfo: CreateChallengeGraph("basic_info")
    object ChallengeLocation: CreateChallengeGraph("challenge_location")
    object CreateClues: CreateChallengeGraph("create_clues")
    object AddClue: CreateChallengeGraph("add_clue")
    object ChallengePreview: CreateChallengeGraph("create_challenge")
}
