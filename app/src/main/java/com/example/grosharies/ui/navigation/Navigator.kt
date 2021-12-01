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
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.EditList
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.groceryList.NewList
import com.example.grosharies.ui.groceryList.StartShopping
import com.example.grosharies.ui.groups.*

@ExperimentalAnimationApi
@Composable
fun Navigator(navController: NavHostController) {
    val context = LocalContext.current
    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModel.GroupViewModelFactory(context.applicationContext as Application)
    )
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )
    val listItemViewModel: ListItemViewModel = viewModel(
        factory = ListItemViewModel.ListItemViewModelFactory(context.applicationContext as Application)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = "root"
    ) {
        composable(route = Screen.Home.route) { Home(navController = navController) }
        groupNavigation(navController, groupViewModel, groceryListViewModel, listItemViewModel)
        listNavigation(navController, groceryListViewModel, listItemViewModel)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.groupNavigation(navController: NavController, groupViewModel: GroupViewModel, groceryListViewModel: GroceryListViewModel, listItemViewModel: ListItemViewModel) {
    navigation(startDestination = Screen.Groups.route, "group") {
        composable(route = Screen.Groups.route) {
            Overview(navController = navController, groupViewModel = groupViewModel)
        }
        composable(route = Screen.GroupName.route) {
            NameInput(navController = navController)
        }
        composable(route = Screen.GroupNew.route) {
            New(navController = navController, groupViewModel = groupViewModel)
        }
        composable(route = Screen.GroupEdit.route + "/{groupId}") { entry ->
            Edit(
                navController = navController,
                groupViewModel = groupViewModel,
                groupId = entry.arguments?.getString("groupId")?.toInt() ?: 1
            )
        }
        composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
            View(
                groupId = entry.arguments?.getString("groupId") ?: "0",
                navController = navController,
                groupViewModel = groupViewModel,
                groceryListViewModel = groceryListViewModel,
                listItemViewModel = listItemViewModel
            )
        }
    }
}

fun NavGraphBuilder.listNavigation(
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    listItemViewModel: ListItemViewModel
) {
    navigation(startDestination = Screen.Lists.route, "list") {
        composable(route = Screen.Lists.route + "/{groupId}") { entry ->
            ListOverview(
                navController = navController,
                groupId = entry.arguments?.getString("groupId") ?: "0",
                groceryListViewModel = groceryListViewModel,
                listItemViewModel = listItemViewModel
            )
        }
        composable(route = Screen.ListEdit.route + "/{groupId}/{listId}") { entry ->
            EditList(
                listId = entry.arguments?.getString("listId") ?: "0",
                navController = navController,
                groceryListViewModel = groceryListViewModel,
                listItemViewModel = listItemViewModel
            )
        }
        composable(route = Screen.StartShopping.route + "/{groupId}/{listId}") { entry ->
            StartShopping(
                groupId = entry.arguments?.getString("groupId") ?: "0",
                listId = entry.arguments?.getString("listId") ?: "0",
                navController = navController,
                groceryListViewModel = groceryListViewModel,
                listItemViewModel = listItemViewModel
            )
        }
        composable(route = Screen.ListNew.route + "/{groupId}") { entry ->
            NewList(
                groupId = entry.arguments?.getString("groupId") ?: "0",
                navController = navController,
                groceryListViewModel = groceryListViewModel,
                listItemViewModel = listItemViewModel
            )
        }
    }
}
