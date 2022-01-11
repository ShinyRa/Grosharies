package com.example.grosharies.ui.groups

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.data.group.Group
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.common.DefaultText
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@ExperimentalAnimationApi
@Composable
fun Overview(
    navController: NavController,
    groupViewModel: GroupViewModel,
    nameInputViewModel: NameInputViewModel,
) {
    setTitle("Groups")

    /*
     * If display name is not found in the database, the user should not be able to view this content
     */
    LaunchedEffect(nameInputViewModel.username.value) {
        if (nameInputViewModel.username.value == null) {
            navController.navigate(Screen.GroupName.route)
        }
    }

    val groups = groupViewModel.groups.value

    GroshariesTheme {
        Surface(
            color = backdrop, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                if (groups.size == 0) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "You don't have any groups yet!",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        )
                        Text(
                            text = "Create or join one to start your shared shopping experience",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.no_groups),
                            contentDescription = "No lists yet!",
                            modifier = Modifier.size(300.dp, 300.dp)
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .weight(6f)
                            .padding(PaddingValues(vertical = 10.dp, horizontal = 8.dp))
                            .verticalScroll(state = ScrollState(0))
                    ) {
                        groups.map { group ->
                            GroupCard(
                                group = group,
                                onClick = { navController.navigate(Screen.Lists.withArgs(group.id.toString())) },
                                deleteGroup = { groupToDelete ->
                                    groupViewModel.deleteGroup(groupToDelete)
                                }
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .weight(1f)
                ) {
                    RoundedButton(text = "Create new group",
                        onClickListener = { navController.navigate(Screen.GroupNew.route) })
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