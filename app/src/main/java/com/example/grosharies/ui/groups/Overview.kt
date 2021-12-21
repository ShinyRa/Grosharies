package com.example.grosharies.ui.groups

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.R
import com.example.grosharies.data.group.Group
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.ui.common.DefaultText
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@ExperimentalAnimationApi
@Composable
fun Overview(navController: NavController, groupViewModel: GroupViewModel, nameInputViewModel: NameInputViewModel) {

    setTitle("Group Overview")

    val groups = groupViewModel.groups.value

    LaunchedEffect(nameInputViewModel.username.value) {
        if (nameInputViewModel.username.value == null) {
            navController.navigate(Screen.GroupName.route)
        }
    }
        GroshariesTheme {
            Surface(
                color = backdrop, modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                groups.map { group ->
                    GroupCard(
                        group = group,
                        onClick = { navController.navigate(Screen.GroupEdit.withArgs(group.id.toString())) },
                        deleteGroup = { groupToDelete ->
                            groupViewModel.deleteGroup(groupToDelete)
                        }
                    )
                }

                    Column(verticalArrangement = Arrangement.Bottom) {
                        RoundedButton(text = "Create",
                            onClickListener = { navController.navigate(Screen.GroupNew.route) })
                        RoundedButton(
                            text = "Join",
                            isSecondary = true,
                            onClickListener = { /*navController.navigate(Screen.GroupEdit.route)*/ })
                    }
                }
            }
    }
}


@ExperimentalAnimationApi
@Composable
fun GroupCard(
    group: Group,
    onClick: () -> Unit,
    deleteGroup: (
        Group,
    ) -> Unit,
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = state,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally()
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(8f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DefaultText(group.name)
                    }
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = { deleteGroup(group) }
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_close_24),
                                contentDescription = "delete"
                            )
                        }
                    }
                }
            }
        }
    }
}