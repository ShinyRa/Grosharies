package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun NameInput(navController: NavController, nameInputViewModel: NameInputViewModel) {
    setTitle("Who are you?")

    val nameValue = remember {
        mutableStateOf(TextFieldValue(""))
    }
    LaunchedEffect(nameInputViewModel.username.value) {
        if (nameInputViewModel.username.value != null) {
            navController.navigate(Screen.Groups.route)
        }
    }

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
            Column(
                modifier = Modifier
                    .weight(7f)
                    .padding(vertical = 32.dp)
            ) {
                InfoText()

                Column {
                    Row {
                        DefaultTextInputField(
                            value = nameValue.value,
                            onChange = { nameValue.value = it },
                            label = "Your name"
                        )
                    }
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                MainButton(text = "Save", onClickListener = {
                    nameInputViewModel.insertNameInput(
                        com.example.grosharies.data.nameInput.NameInput(
                            name = nameValue.value.text
                        )
                    )
                })
            }
        }
    }
}

@Composable
fun InfoText() {
    Text(
        "To use our collaborative features, please provide a display name to identify yourself",
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp)
    )
}

