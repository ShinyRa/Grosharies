package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grosharies.ui.groceryList.CreateListOverview

val GROUP_VIEW_ROUTE: String = "group/view/{groupId}"

@Composable
fun GroupView(groupId: String?) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(16.dp)) {
        CreateListOverview(groupId)
    }
}