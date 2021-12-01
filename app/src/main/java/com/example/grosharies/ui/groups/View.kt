package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.groceryList.ListOverview

@Composable
fun View(groupId: String, navController: NavController, groupViewModel: GroupViewModel, groceryListViewModel: GroceryListViewModel, listItemViewModel: ListItemViewModel) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(16.dp)) {
        ListOverview(groupId, navController = navController, groceryListViewModel = groceryListViewModel, listItemViewModel = listItemViewModel)
    }
}