package com.example.grosharies.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.grosharies.ui.theme.PrimaryColor

@Composable
fun DefaultText(text: String) {
    Text(text = text)
}

@Composable
fun DefaultTextInputField(
    label: String,
    value: TextFieldValue,
    onChange: (TextFieldValue) -> Unit,
    modifier: Modifier? = null,
    keyboardType: KeyboardType? = null
) {
    TextField(
        value = value,
        onValueChange = { if (!it.text.contains("\n")) onChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType ?: KeyboardType.Text
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .composed { modifier ?: Modifier }
    )
}
