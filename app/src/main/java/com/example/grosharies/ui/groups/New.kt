package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.ui.common.DefaultText

@Composable
fun New(
    addGroup: (group: Group) -> Unit,
    navController: NavController
) {
    Surface(modifier = Modifier.padding(16.dp)) {
        DefaultText("New!")
        
        Button(onClick = {
            addGroup(Group(1, "Test"))
            navController.popBackStack()
        }) {
            DefaultText(text = "Click")
        }
    }
}