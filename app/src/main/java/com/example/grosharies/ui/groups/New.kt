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
    navController: NavController,
) {
    Surface(modifier = Modifier.padding(16.dp)) {
        DefaultText("New!")

        Button(onClick = {
            navController.popBackStack()
        }) {
            DefaultText(text = "Click")
        }
    }
}