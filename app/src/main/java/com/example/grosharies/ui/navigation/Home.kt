package com.example.grosharies.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun Home() {
    GroshariesTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Text("Home")
        }
    }
}