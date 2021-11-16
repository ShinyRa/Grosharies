package com.example.grosharies.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.grosharies.ui.Home
import com.example.grosharies.ui.groceryList.CreateListOverview
import com.example.grosharies.ui.groups.*

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
                    composable(route = Screen.Home.route) { Home() }
                    composable(route = Screen.Lists.route) { CreateListOverview() }
                    navigation(startDestination = Screen.Groups.route, "group") {
                        composable(route = Screen.Groups.route) {
                            Overview(navController = navController)
                        }
                        composable(route = Screen.GroupNew.route) { New(addGroup = { group: Group -> addGroup(group) }, navController = navController) }
                        composable(route = Screen.GroupEdit.route) { Edit() }
                        composable(route = Screen.GroupDetail.route + "/{groupId}") { entry ->
                            GroupView(groupId = entry.arguments?.getString("groupId"))
                        }
                    }
                }
            }
        },
        bottomBar = { BottomNavigator(navController) }
    )
}




