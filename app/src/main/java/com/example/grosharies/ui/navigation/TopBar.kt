package com.example.grosharies.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.grosharies.R

class TopBarState(
    val title: String,
    val actions: Array<TopBarAction?>,
)

class TopBarAction(
    val iconResource: Int,
    val action: () -> Void,
)

// Still not working because don't know how to get navController as a reference instead of immutable copy grrrrrrr
fun hasBack(queue: ArrayDeque<NavBackStackEntry>): Boolean = queue.size > 0

private var title: MutableState<String> = mutableStateOf("")
private var actions: MutableState<List<TopBarAction>> = mutableStateOf(listOf())

fun setTitle(it: String) {
    title.value = it
}

fun setActions(it: List<TopBarAction>) {
    actions.value = it
}

@Composable
fun TopBar(navController: NavController) {
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
//                stringResource(
//                    id = Screen.findByRoute(currentRoute ?: "home")?.nameResource ?: R.string.home
//                )
                title.value
            )
        },
        actions = {
            actions
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
