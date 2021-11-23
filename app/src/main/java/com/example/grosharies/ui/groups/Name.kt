package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen

@Composable
fun NameInput(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
    ) {
        InfoText()
        val query = remember {
            mutableStateOf("Name")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TextField(value = query.value,
                onValueChange = { query.value = it })
        }
    }
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

