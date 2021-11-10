package com.example.grosharies.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun Home() {
    GroshariesTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()) {
                Grosharies()
                Text()

                MRL()

                Groups()

                Ps()
            }
        }
    }
}


@Composable
fun Grosharies() {
    Text("Grosharies \n", fontSize = 50.sp, color = Color.Green, style = TextStyle(
        textDecoration = TextDecoration.combine(
            listOf(
                TextDecoration.Underline,

                )
        ), fontWeight = FontWeight.Bold)

    )
}

@Composable
fun Text() {
    Text("Use this app with your friends to have a collaborative grocery list that you can share easily! \n \n \n",
        fontSize = 15.sp,
        textAlign = TextAlign.Justify,
        modifier = Modifier.width(150.dp))
}

@Composable
fun MRL() {
    OutlinedButton(onClick = { /* Do something! */ }) {
        Text(" \n 🧾Most recent list \n", fontSize = 20.sp)

    }
}

@Composable
fun Groups() {
    OutlinedButton(onClick = { /* Do something! */ }) {
        Text(" \n ‍👩‍👧‍👧Groups \n", fontSize = 20.sp)
    }
}

@Composable
fun Ps() {
    OutlinedButton(onClick = { /* Do something! */ }) {
        Text(" \n 🙎‍♂️Personal list \n", fontSize = 20.sp)
    }
}