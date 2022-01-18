package com.example.grosharies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.grosharies.ui.navigation.DefaultScaffold
import com.example.grosharies.ui.theme.GroshariesTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroshariesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    DefaultScaffold()
                }
            }
        }
    }
}
