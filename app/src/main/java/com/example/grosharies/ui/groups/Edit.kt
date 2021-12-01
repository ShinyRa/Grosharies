package com.example.grosharies.ui.groups

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.grosharies.data.Group.GroupViewModel

@Composable
fun Edit(
    navController: NavController,
    groupViewModel: GroupViewModel,
    groupId: Int
) {
    New(navController = navController, groupViewModel = groupViewModel, groupId = groupId)
}