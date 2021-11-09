package com.example.grosharies.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.grosharies.R

// Still not working because don't know how to get navController as a reference instead of immutable copy grrrrrrr
fun hasBack(queue: ArrayDeque<NavBackStackEntry>): Boolean = queue.size > 0

@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (hasBack(queue = navController.backQueue)) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(painter = painterResource(
                        id = R.drawable.ic_chevron_left_24),
                        contentDescription = "Back")
                }
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}