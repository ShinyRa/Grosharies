package com.example.grosharies.ui.groups

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.MaterialTheme
import com.example.grosharies.ui.theme.GroshariesTheme

class GroupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroshariesTheme {
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}