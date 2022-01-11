package com.example.grosharies.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.example.grosharies.ui.theme.PrimaryColor

@Composable
fun Input(
    label: String,
    value: TextFieldValue,
    onChange: (TextFieldValue) -> Unit,
    modifier: Modifier? = null
) {
    TextField(
        value = value,
        onValueChange = { onChange(it) },
        label = { Text(text = label) },
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