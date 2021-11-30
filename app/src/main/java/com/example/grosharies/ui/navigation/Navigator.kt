package com.example.grosharies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.grosharies.data.NameInput.NameInput
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.EditList
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.groceryList.NewList
import com.example.grosharies.ui.groceryList.StartShopping
import com.example.grosharies.ui.groups.*
import com.example.grosharies.ui.groups.NameInput

@Composable
fun Navigator(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = "root"
    ) {
        composable(route = Screen.Home.route) { Home(navController = navController) }
        groupNavigation(navController)
        listNavigation(navController)
    }
}

fun NavGraphBuilder.groupNavigation(navController: NavController) {
    navigation(startDestination = Screen.Groups.route, "group") {
        composable(route = Screen.Groups.route) {
            Overview(navController = navController)
        }
        composable(route = Screen.GroupName.route){
            NameInput(navController = navController)
        }
        composable(route = Screen.GroupNew.route) {
            New(navController = navController)
        }
        composable(route = Screen.GroupEdit.route) { Edit() }
        composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
            View(
                groupId = entry.arguments?.getString("groupId") ?: "0",
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.listNavigation(navController: NavController) {
    navigation(startDestination = Screen.Lists.route, "list") {
        composable(route = Screen.Lists.route + "/{groupId}") { entry ->
            ListOverview(
                navController = navController,
                groupId = entry.arguments?.getString("groupId") ?: "0"
            )
        }
        composable(route = Screen.ListEdit.route + "/{groupId}/{listId}") { entry ->
            EditList(
                entry.arguments?.getString("groupId") ?: "0",
                entry.arguments?.getString("listId") ?: "0",
                navController = navController
            )
        }
        composable(route = Screen.StartShopping.route + "/{groupId}/{listId}") { entry ->
            StartShopping(
                entry.arguments?.getString("groupId") ?: "0",
                entry.arguments?.getString("listId") ?: "0",
                navController = navController
            )
        }
        composable(route = Screen.ListNew.route + "/{groupId}") { entry ->
            NewList(
                entry.arguments?.getString("groupId") ?: "0",
                navController = navController
            )
        }
    }
}
