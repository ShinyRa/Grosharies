package com.example.grosharies.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.ui.theme.GroshariesTheme

@ExperimentalAnimationApi
@Composable
fun DefaultScaffold() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navController) },
        content = {
            GroshariesTheme {
                Surface(modifier = Modifier.padding(it)) {
                    Navigator(navController = navController)
                }
            }
        },
        bottomBar = { BottomNavigator(navController) }
    )
}