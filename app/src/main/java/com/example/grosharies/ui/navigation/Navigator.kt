package com.example.grosharies.ui.navigation

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.grosharies.data.NameInput.NameInput
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.EditList
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.groceryList.NewList
import com.example.grosharies.ui.groceryList.StartShopping
import com.example.grosharies.ui.groups.*
import com.example.grosharies.ui.groups.NameInput

@ExperimentalAnimationApi
@Composable
fun Navigator(navController: NavHostController) {
    val context = LocalContext.current
    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModel.GroupViewModelFactory(context.applicationContext as Application)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = "root"
    ) {
        composable(route = Screen.Home.route) { Home(navController = navController) }
        groupNavigation(navController, groupViewModel)
        listNavigation(navController)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.groupNavigation(navController: NavController, groupViewModel: GroupViewModel) {
    navigation(startDestination = Screen.Groups.route, "group") {
        composable(route = Screen.Groups.route) {
            Overview(navController = navController, groupViewModel = groupViewModel)
        }
        composable(route = Screen.GroupName.route){
            NameInput(navController = navController)
        }
        composable(route = Screen.GroupNew.route) {
            New(navController = navController, groupViewModel = groupViewModel)
        }
        composable(route = Screen.GroupEdit.route + "/{groupId}") { entry ->
            Edit(navController = navController,
                groupViewModel = groupViewModel,
                groupId = entry.arguments?.getString("groupId")?.toInt() ?: 1)
        }
        composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
            View(
                groupId = entry.arguments?.getString("groupId") ?: "0",
                navController = navController,
                groupViewModel = groupViewModel
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
