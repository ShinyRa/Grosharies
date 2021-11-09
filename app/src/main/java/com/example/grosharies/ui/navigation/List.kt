package com.example.grosharies.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun Lists() {
    GroshariesTheme {
        Surface(color = MaterialTheme.colors.background) {
            Text("Lists")
        }
    }
}