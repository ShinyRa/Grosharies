package com.example.grosharies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.grosharies.data.GroceryList.getExampleData
import com.example.grosharies.ui.navigation.DefaultScaffold
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.background2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroshariesTheme {
                DefaultScaffold()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroshariesTheme {
        Surface(color = background2) {
            CreateListOverview(getExampleData())
        }
    }
}