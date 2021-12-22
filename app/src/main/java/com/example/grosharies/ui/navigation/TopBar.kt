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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.R


class TopBarAction(
    val iconResource: Int,
    val action: () -> Unit,
)

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
    val (hasBack, setHasBack) = remember {
        mutableStateOf<Boolean>(false)
    }
    navController.addOnDestinationChangedListener { controller, destination, _ ->
        setHasBack(controller.backQueue.size > 2)
    }
    TopAppBar(
        title = {
            Text(title.value)
        },
        actions = {
            actions.value.map {
                IconButton(
                    onClick = { it.action() }
                ) {
                    Icon(
                        painterResource(id = it.iconResource),
                        contentDescription = "delete"
                    )
                }
            }
        },
        navigationIcon = {
            if (hasBack) {
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
