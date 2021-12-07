package com.example.grosharies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
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
                setTitle("Home")

                Grosharies()
                Text()

                Screenimage()

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
fun Screenimage() {
    Row {
        Image(
            painter = painterResource(R.drawable.gro_im1),
            contentDescription = "Woman grosharing",
            modifier = Modifier
                .size(250.dp,100.dp)
        )
    }

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
            onClickListener = { navController.navigate(Screen.GroupName.route) })

        RoundedButton(
            text = "Personal List",
            onClickListener = { navController.navigate(Screen.Lists.withArgs("0")) })
    }
}