package com.example.grosharies.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.EditList
import com.example.grosharies.ui.groups.GroupOverview
import com.example.grosharies.ui.groups.GroupView

@Composable
fun DefaultScaffold() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navController) },
        content = {
            Surface {
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = Screen.Home.route) { Home() }
                    composable(route = Screen.Groups.route) { GroupOverview(navController) }
                    composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
                        GroupView(
                            entry.arguments?.getString("groupId"),
                            navController = navController
                        )
                    }
                    composable(route = Screen.Lists.route) { ListOverview(navController = navController) }
                    composable(route = Screen.ListEdit.route + "/{groupId}/{listId}") { entry ->
                        EditList(
                            entry.arguments?.getString("groupId"),
                            entry.arguments?.getString("listId"),
                            navController = navController
                        )
                    }
                }
            }
        },
        bottomBar = { BottomNavigator(navController) }
    )
}