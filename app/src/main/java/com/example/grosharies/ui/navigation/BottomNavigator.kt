package com.example.grosharies.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.ui.theme.PrimaryColor
import com.example.grosharies.ui.theme.textColor

data class BottomNavigatorItem(
    val name: String,
    val routes: Array<String>,
    val id: Int,
    val navigate: () -> Unit,
)

@Composable
fun BottomNavigator(navController: NavController) {
    val listItems = listOf(
        BottomNavigatorItem(
            "Home",
            arrayOf(Screen.Home.route),
            R.drawable.ic_home_24
        ) { navController.navigate(Screen.Home.route) },
        BottomNavigatorItem(
            "Groups",
            arrayOf(
                Screen.Groups.route,
                Screen.GroupDetail.route,
                Screen.GroupNew.route,
                Screen.GroupEdit.route
            ),
            R.drawable.ic_group_24
        ) { navController.navigate(Screen.Groups.route) },
        BottomNavigatorItem(
            "My lists",
            arrayOf(Screen.Lists.route, Screen.ListEdit.route, Screen.StartShopping.route),
            R.drawable.ic_list_24
        ) { navController.navigate(Screen.Lists.withArgs("0")) }
    )

    val (currentRoute, setCurrentRoute) = remember { mutableStateOf<String?>("") }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        run {
            setCurrentRoute(destination.route)
        }
    }

    BottomAppBar(backgroundColor = Color.White, content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            listItems.forEachIndexed { _, bottomNavigatorItem ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = bottomNavigatorItem.id),
                            contentDescription = bottomNavigatorItem.name
                        )
                    },
                    label = { Text(text = bottomNavigatorItem.name) },
                    selected = bottomNavigatorItem.routes.asList().contains(currentRoute),
                    onClick = { bottomNavigatorItem.navigate() },
                    selectedContentColor = PrimaryColor,
                    unselectedContentColor = textColor
                )
            }
        }
    })
}