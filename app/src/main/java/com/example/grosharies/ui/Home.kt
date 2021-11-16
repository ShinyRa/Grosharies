package com.example.grosharies.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.PrimaryColor

@Composable
fun Home(navController: NavController) {
    GroshariesTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Grosharies()
                Text()

                ButtonsHomeScreen(navController)

            }
        }
    }
}


@Composable
fun Grosharies() {
    Text("Grosharies \n", fontSize = 50.sp, color = PrimaryColor, fontWeight = FontWeight.Bold)

}

@Composable
fun Text() {
    Text(
        "Use this app with your friends to have a collaborative grocery list that you can share easily! \n \n \n",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun ButtonsHomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RoundedButton(text = "Most recent list", isSecondary = true, onClickListener = { /*TODO*/ })

        RoundedButton(
            text = "Groups",
            onClickListener = { navController.navigate(Screen.Groups.route) })

        RoundedButton(
            text = "Personal List",
            onClickListener = { navController.navigate(Screen.Lists.route) })
    }
}


/*@Composable
fun Groups() {
    //OutlinedButton(onClick = { /* Do something! */ }) {
        //Text(" \n ‍👩‍👧‍👧Groups \n", fontSize = 20.sp) }
    RoundedButton(text = "Groups", onClickListener = { /*TODO*/})
}

@Composable
fun PersonalList() {
    //OutlinedButton(onClick = { /* Do something! */ }) {
        //Text(" \n 🙎‍♂️Personal list \n", fontSize = 20.sp)}
    RoundedButton(text = "Personal List", onClickListener = { /*TODO*/})

}*/