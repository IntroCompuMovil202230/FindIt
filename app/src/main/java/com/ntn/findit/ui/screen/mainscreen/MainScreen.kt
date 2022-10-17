package com.ntn.findit.ui.screen.mainscreen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ntn.findit.ui.screen.mainscreen.navigation.BottomNavigationBar
import com.ntn.findit.ui.screen.mainscreen.navigation.MainScreenNavigationGraph


@Composable
fun MainScreenView(principalNavController: NavHostController){

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
        topBar = {},
    ) { MainScreenNavigationGraph(navController = navController) }
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
        icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = "sdf") },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.screen_route
        } == true,
        onClick = {navController.navigate(screen.screen_route)}
    )
}

@Composable
@Preview(showSystemUi = true)
fun Preview(){
    MainScreenView(rememberNavController())
}