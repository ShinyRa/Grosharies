package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.ui.ButtonsHomeScreen
import com.example.grosharies.ui.Grosharies
import com.example.grosharies.ui.Text
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun NameInput(navController: NavController) {
    InfoText()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        MainButton(text = "SAVE", onClickListener = { navController.navigate(Screen.Groups.route) })

    }
}

@Composable
fun InfoText() {
    androidx.compose.material.Text(
        "Enter your name here",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

