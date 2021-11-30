package com.example.grosharies.ui.groups

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.ui.common.DefaultText

@Composable
fun Edit(
    navController: NavController,
    groupViewModel: GroupViewModel,
    groupId: Int
) {
    New(navController = navController, groupViewModel = groupViewModel, groupId = groupId)
}