package com.example.grosharies.ui.common

import android.view.View
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
fun MainButton(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Create a Main Button or Normal Button
    Button(onClick = {onClickListener()}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor)){

        Text(text = text, color = color )
    }
}

@Composable
fun TextButton(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Create a Text Button
    TextButton(onClick = { onClickListener() }, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
        backgroundColor = PrimaryColor)) {
        Text(text = text, color = color)
    }
}

@Composable
fun ElevatedButton(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Elevated Button
    Button(
        onClick = { onClickListener() },
        modifier = Modifier.padding(8.dp),
        elevation = ButtonDefaults.elevation(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun RoundedButton(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Rounded Button
    Button(
        onClick = { onClickListener() },
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun OutlinedButton(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Outlined Button
    // add border field and set BorderStroke and its color
    OutlinedButton(
        onClick = { onClickListener() },
        border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun OutlinedBuittonWithIcon(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Outlined Button with icon
    // add border field and set BorderStroke and its color
    // add Icon field
    OutlinedButton(
        onClick = { onClickListener() },
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
fun IconButtonLeftSide(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Icon Button
    // Icon on the left of text
    Button(onClick = {onClickListener()}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
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
fun IconButtonRightSide(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
    // Icon Button
    // Icon on the Right of text
    Button(onClick = {onClickListener()}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
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
fun CustomBackgroundButtons(text: String, color: Color = Color.White, onClickListener: ()-> Unit) {
//custom background buttons
// create a variable mainButtonColor and define background Color and content Color
    val mainButtonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color.Magenta,
        contentColor = MaterialTheme.colors.surface
    )

    Row {
        Button(onClick = {onClickListener()}, modifier = Modifier.padding(8.dp), colors = ButtonDefaults.textButtonColors(
            backgroundColor = PrimaryColor)) {
            Text(text = text, color = color)
        }
    }
}
