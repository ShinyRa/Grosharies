package com.example.grosharies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.grosharies.ui.theme.textColorSecondary

@Composable
fun Home(navController: NavController) {
    GroshariesTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                setTitle("")
                Grosharies()
                WelcomeTextOnHomeScreen()

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
fun WelcomeTextOnHomeScreen() {
    Text(
        "Use this app with your friends to have a collaborative grocery list that you can share easily!",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        color = textColorSecondary,
        modifier = Modifier
            .fillMaxWidth().padding(8.dp)
    )
}


@Composable
fun Screenimage() {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(R.drawable.gro_im1),
            contentDescription = "Woman grosharing",
            modifier = Modifier.fillMaxWidth()
//                .size(250.dp, 100.dp)

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
//        RoundedButton(text = "Most recent list", isSecondary = true, onClickListener = { /*TODO*/ })

        RoundedButton(
            text = "Groups",
            onClickListener = { navController.navigate(Screen.Groups.route) })

        RoundedButton(
            text = "Personal List",
            onClickListener = { navController.navigate(Screen.Lists.withArgs("0")) })
    }
}