package com.example.grosharies.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.ui.theme.primary

@Composable
fun DefaultScaffold() {
        val navController = rememberNavController()
        Scaffold(
            topBar = { TopAppBar(title = { Text("Grosharies") }, backgroundColor = primary)  },
            content = {
                Surface() {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") { Home() }
                        composable(route = "group") { Groups() }
                        composable(route = "list") { Lists() }
                    }
                }
            },
            bottomBar = { BottomNavigator(navController) }
        )
}