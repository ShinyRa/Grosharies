package com.example.grosharies.ui.common

import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue


@Composable

fun DefaultText(text: String) {
    Text(text = text)
}

@Composable
fun DefaultTextInputField(
    text: String,
    modifier: Modifier?,
    textValue: MutableState<TextFieldValue>

) {
    // var textValue by remember { mutableStateOf("") }

    TextField(
        value = textValue.value,
        onValueChange = {
            if (!it.text.contains("\n"))
            textValue.value = it
        },
        label = { Text(text = text) },

        modifier = Modifier
            .fillMaxWidth()
//            .padding(PaddingValues(
//            top = 8.dp,
//            bottom = 8.dp,
//            end = 4.dp))
            .composed { modifier ?: Modifier }
    )
}
