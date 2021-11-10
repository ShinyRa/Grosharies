package com.example.grosharies.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.groups.GROUP_OVERVIEW_ROUTE
import com.example.grosharies.ui.groups.GROUP_VIEW_ROUTE
import com.example.grosharies.ui.groups.GroupOverview
import com.example.grosharies.ui.groups.GroupView
import com.example.grosharies.ui.theme.primary

@Composable
fun DefaultScaffold() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Grosharies") }, backgroundColor = primary)  },
        content = {
            Surface {
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = "home") { Home() }
                    navigation(startDestination = "group/view", route = "group") {
                        composable(GROUP_OVERVIEW_ROUTE) { GroupOverview(navController) }
                        composable(GROUP_VIEW_ROUTE) { entry ->
                            GroupView(entry.arguments?.getString("groupId"))
                        }
                    }
                    composable(route = "list") { ListOverview() }
                }
            }
        },
        bottomBar = { BottomNavigator(navController) }
    )
}