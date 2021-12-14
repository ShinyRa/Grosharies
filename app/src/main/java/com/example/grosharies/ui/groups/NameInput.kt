package com.example.grosharies.ui.groups

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun NameInput(navController: NavController) {
    val nameValue = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val context = LocalContext.current
    val nameInputViewModel: NameInputViewModel = viewModel(
        factory = NameInputViewModel.NameInputViewModelFactory(context.applicationContext as Application)
    )
    setTitle("Who are you?")

    GroshariesTheme {
        Column(
            modifier = Modifier
                .padding(
                    PaddingValues(
                        start = 32.dp,
                        end = 32.dp,
                    )
                )
                .fillMaxSize()
        ) {
            InfoText()

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row {
                    DefaultTextInputField(
                        text = "Your Name", modifier = Modifier
                            .fillMaxWidth(), textValue = nameValue
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            MainButton(text = "SAVE", onClickListener = {
                navController.navigate(Screen.Groups.route)
                nameInputViewModel.insertNameInput(
                    com.example.grosharies.data.nameInput.NameInput(
                        name = nameValue.value.text
                    )
                )
            })
        }
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

