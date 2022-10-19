package com.ntn.findit.ui.screen.mainscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.mainscreen.navigation.BottomNavigationBar
import com.ntn.findit.ui.screen.mainscreen.navigation.HomeNavGraph


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(navController: NavHostController = rememberNavController()){
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Log.d("Mio", "Test: ${navBackStackEntry?.destination?.route}")
    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "challenges" -> true
        "mychallenges" -> true
        "chat" -> true
        "account" -> true
        else -> false // in all other cases show bottom bar
    }
    Scaffold(
        bottomBar = {  if (showBottomBar)  BottomBar(navController = navController)},
        topBar = {},

    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationBar.Challenges,
        BottomNavigationBar.MyChallenges,
        BottomNavigationBar.Chat,
        BottomNavigationBar.Account
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        items.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationBar,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = { Text(screen.title)},
        icon = { Icon(imageVector = screen.icon, contentDescription = "sdf") },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {navController.navigate(screen.route)}
    )
}

@Composable
@Preview(showSystemUi = true)
fun Preview(){
    MainScreenView(rememberNavController())
}