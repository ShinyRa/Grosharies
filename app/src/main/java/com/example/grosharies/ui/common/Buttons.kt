package com.example.grosharies.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.grosharies.ui.theme.PrimaryColor

@Composable
fun mainButton(text: String, color: Color = Color.White) {
    // Create a Main Button or Normal Button
    Button(onClick = {}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor) ){

        Text(text = text, color = color )
    }
}

@Composable
fun textButton(text: String, color: Color = Color.White) {
    // Create a Text Button
    TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor)) {
        Text(text = text, color = color)
    }
}

@Composable
fun elevatedButton(text: String, color: Color = Color.White) {
    // Elevated Button
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(8.dp),
        elevation = ButtonDefaults.elevation(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun roundedButton(text: String, color: Color = Color.White) {
    // Rounded Button
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun outlinedButton(text: String, color: Color = Color.White) {
    // Outlined Button
    // add border field and set BorderStroke and its color
    OutlinedButton(
        onClick = { /*TODO*/ },
        border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun outlinedBuittonWithIcon(text: String, color: Color = Color.White) {
    // Outlined Button with icon
    // add border field and set BorderStroke and its color
    // add Icon field
    OutlinedButton(
        onClick = { /*TODO*/ },
        border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(text = text, color = color)
    }
}

@Composable
fun iconButtonLeftSide(text: String, color: Color = Color.White) {
    // Icon Button
    // Icon on the left of text
    Button(onClick = {}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor)) {
        Row {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = text, color = color)
        }
    }
}

@Composable
fun iconButtonRightSide(text: String, color: Color = Color.White) {
    // Icon Button
    // Icon on the Right of text
    Button(onClick = {}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor)) {
        Text(text = text, color = color)
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun customBackgroundButtons(text: String, color: Color = Color.White) {
//custom background buttons
// create a variable mainButtonColor and define background Color and content Color
    val mainButtonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color.Magenta,
        contentColor = MaterialTheme.colors.surface
    )

    Row {
        Button(onClick = {}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)) {
            Text(text = text, color = color)
        }
    }
}
