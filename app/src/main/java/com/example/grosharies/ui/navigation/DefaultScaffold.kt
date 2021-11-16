package com.example.grosharies.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.ui.groceryList.ListOverview
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groups.*
import com.example.grosharies.ui.groceryList.EditList
import com.example.grosharies.ui.groceryList.StartShopping

@Composable
fun DefaultScaffold() {
    val navController = rememberNavController()

    val (groups, setGroups) = remember {
        mutableStateOf(listOf<Group>())
    }

    val counter = remember { mutableStateOf(0) }

    fun addGroup(group: Group) {
        counter.value = counter.value + 1
        setGroups(groups + Group(counter.value, group.name))
    }

    fun removeFromGroups(group: Group) = setGroups(groups.filter { it.id != group.id })

    Scaffold(
        topBar = { TopBar(navController) },
        content = {
            Surface(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = Screen.Home.route, route = "root") {
                    composable(route = Screen.Home.route) { Home(navController = navController) }
                    navigation(startDestination = Screen.Groups.route, "group") {
                        composable(route = Screen.Groups.route) {
                            Overview(navController = navController)
                        }
                        composable(route = Screen.GroupNew.route) { New(addGroup = { group: Group -> addGroup(group) }, navController = navController) }
                        composable(route = Screen.GroupEdit.route) { Edit() }
                        composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
                            View(groupId = entry.arguments?.getString("groupId"), navController = navController)
                        }
                    }
                    composable(route = Screen.Lists.route) { ListOverview(navController = navController) }
                    composable(route = Screen.ListEdit.route + "/{groupId}/{listId}") { entry ->
                        EditList(
                            entry.arguments?.getString("groupId"),
                            entry.arguments?.getString("listId"),
                            navController = navController
                        )
                    }
                    composable(route = Screen.StartShopping.route + "/{groupId}/{listId}") { entry ->
                        StartShopping(
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