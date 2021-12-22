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
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.*
import com.example.grosharies.ui.groups.*
import com.example.grosharies.ui.listItem.ListDetail
import com.example.grosharies.ui.listItem.ListItemNew

@ExperimentalAnimationApi
@Composable
fun Navigator(navController: NavHostController) {
    val context = LocalContext.current
    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModel.GroupViewModelFactory(context.applicationContext as Application)
    )
    val nameInputViewModel: NameInputViewModel = viewModel(
        factory = NameInputViewModel.NameInputViewModelFactory(context.applicationContext as Application)
    )
    val listItemViewModel: ListItemViewModel = viewModel(
        factory = ListItemViewModel.ListItemViewModelFactory(context.applicationContext as Application)
    )
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = "root"
    ) {
        composable(route = Screen.Home.route) { Home(navController = navController) }
        groupNavigation(navController, groupViewModel, listItemViewModel, nameInputViewModel)
        listNavigation(navController, groceryListViewModel, listItemViewModel, nameInputViewModel)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.groupNavigation(
    navController: NavController,
    groupViewModel: GroupViewModel,
    listItemViewModel: ListItemViewModel,
    nameInputViewModel: NameInputViewModel
) {
    navigation(startDestination = Screen.Groups.route, "group") {
        composable(route = Screen.Groups.route) {
            Overview(
                navController = navController,
                groupViewModel = groupViewModel,
                nameInputViewModel = nameInputViewModel
            )
        }
        composable(route = Screen.GroupName.route) {
            NameInput(navController = navController, nameInputViewModel = nameInputViewModel)
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
                listItemViewModel = listItemViewModel
            )
        }
    }
}

fun NavGraphBuilder.listNavigation(
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    listItemViewModel: ListItemViewModel,
    nameInputViewModel: NameInputViewModel
) {
    navigation(startDestination = Screen.Lists.route, "list") {
        composable(route = Screen.Lists.route + "/{groupId}") { entry ->
            ListOverview(
                navController = navController,
                groupId = entry.arguments?.getString("groupId") ?: "0",
                listItemViewModel = listItemViewModel
            )
        }
        composable(route = Screen.ListEdit.route + "/{groupId}/{listId}") { entry ->
            EditList(
                entry.arguments?.getString("groupId") ?: "0",
                entry.arguments?.getString("listId") ?: "0",
                navController = navController
            )
        }
        composable(route = Screen.StartShopping.route) {
            StartShopping(
                listItemViewModel = listItemViewModel,
                navController = navController
            )
        }
        composable(route = Screen.ListNew.route + "/{groupId}") { entry ->
            NewList(
                entry.arguments?.getString("groupId") ?: "0",
                navController = navController,
                listItemViewModel = listItemViewModel,
                groceryListViewModel = groceryListViewModel,
                nameInputViewModel= nameInputViewModel,
            )
        }
        composable(route = Screen.ListDetail.route + "/{listId}") { entry ->
            ListDetail(
                listId = entry.arguments?.getString("listId") ?: "0",
                navController = navController,
                listItemViewModel = listItemViewModel
            )
        }
        composable(route = Screen.ListItemNew.route + "/{listId}/{listItemId}") { entry ->
            ListItemNew(
                listId = entry.arguments?.getString("listId")?.toInt() ?: 0,
                listItemId = entry.arguments?.getString("listItemId")?.toInt() ?: 0,
                navController = navController,
                listItemViewModel = listItemViewModel
            )
        }
    }
}
