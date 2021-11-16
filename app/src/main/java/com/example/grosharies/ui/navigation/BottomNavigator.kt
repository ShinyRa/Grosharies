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
import com.example.grosharies.ui.theme.primary
import com.example.grosharies.ui.theme.textColor

data class BottomNavigatorItem(val name: String, val id: Int, val navigate: () -> Unit)

@Composable
fun BottomNavigator(navController: NavController) {

    val (selected, setSelected) = remember { mutableStateOf(0) }

    val listItems = listOf(
        BottomNavigatorItem(
            "home",
            R.drawable.ic_home_24
        ) { navController.navigate(Screen.Home.route) },
        BottomNavigatorItem(
            "groups",
            R.drawable.ic_group_24
        ) { navController.navigate(Screen.Groups.route) },
        BottomNavigatorItem(
            "lists",
            R.drawable.ic_list_24
        ) { navController.navigate(Screen.Lists.route) }
    )

    fun switch(active: Int) {
        setSelected(active)
        listItems[active].navigate()
    }

    BottomAppBar(backgroundColor = Color.White, content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            listItems.forEachIndexed { index, bottomNavigatorItem ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = bottomNavigatorItem.id),
                            contentDescription = bottomNavigatorItem.name
                        )
                    },
                    label = { Text(text = bottomNavigatorItem.name) },
                    selected = selected == index,
                    onClick = { switch(index) },
                    selectedContentColor = primary,
                    unselectedContentColor = textColor
                )
            }
        }
    })
}