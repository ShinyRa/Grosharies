package com.example.grosharies.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.grosharies.R

class TopBarState(
    val title: String,
    val actions: Array<TopBarAction?>
)

class TopBarAction(
    val iconResource: Int,
    val action: () -> Void
)

// Still not working because don't know how to get navController as a reference instead of immutable copy grrrrrrr
fun hasBack(queue: ArrayDeque<NavBackStackEntry>): Boolean = queue.size > 0

@Composable
fun TopBar(navController: NavController, state: TopBarState) {
    val (currentRoute, setCurrentRoute) = remember { mutableStateOf<String?>("") }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        run {
            setCurrentRoute(destination.route)
        }
    }
    TopAppBar(
        title = {
//            Text(state.title) ?: "Not found"
            Text(
                stringResource(
                    id = Screen.findByRoute(currentRoute ?: "home")?.nameResource ?: R.string.home
                )
            )
        },
        navigationIcon = {
            if (hasBack(queue = navController.backQueue)) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_chevron_left_24
                        ),
                        contentDescription = "Back"
                    )
                }
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}