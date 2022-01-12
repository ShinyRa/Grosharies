package com.example.grosharies.ui.groups

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.groceryList.ListOverview

@Composable
fun View(groupId: String, navController: NavController, listItemViewModel: ListItemViewModel) {
    ListOverview(groupId, navController = navController, listItemViewModel = listItemViewModel)
}