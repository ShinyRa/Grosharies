package com.example.grosharies.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@ExperimentalAnimationApi
@Composable
fun DefaultScaffold() {
    val navController = rememberNavController()
    val (topBarState, setTopBarState) = remember { mutableStateOf(TopBarState("Test", arrayOf())) }
    val setTitle = { title: String -> setTopBarState(TopBarState(title = title, actions = topBarState.actions)) }

    Scaffold(
        topBar = { TopBar(navController, topBarState) },
        content = {
            Surface(modifier = Modifier.padding(it)) {
                Navigator(navController = navController)
            }
        },
        bottomBar = { BottomNavigator(navController) }
    )
}