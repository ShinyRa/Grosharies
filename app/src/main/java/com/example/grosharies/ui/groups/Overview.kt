package com.example.grosharies.ui.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.ui.common.DefaultText
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@Composable
fun Overview(navController: NavController) {
    val (groups, setGroups) = remember {
        mutableStateOf(
            listOf<Group>()
        )
    }
    val counter = remember { mutableStateOf(0) }

    fun addGroup() {
        navController.navigate(Screen.GroupNew.route)
    }

    fun removeFromGroups(group: Group) = setGroups(groups.filter { it.id != group.id })

    GroshariesTheme {
        Surface(
            color = backdrop, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingValues(vertical = 16.dp, horizontal = 8.dp))
            ) {
                groups.map { group ->
                    GroupCard(
                        group = group,
                        onClick = { navController.navigate("group/view/${group.id}") },
                        deleteGroup = { group -> removeFromGroups(group) }
                    )
                }

                Column(verticalArrangement = Arrangement.Bottom) {
                    RoundedButton(text = "Create", onClickListener = { addGroup() })
                    RoundedButton(text = "Join", isSecondary = true, onClickListener = { navController.navigate(Screen.GroupEdit.route) })
                }
            }
        }
    }
}

@Composable
fun GroupCard(
    group: Group,
    onClick: () -> Unit,
    deleteGroup: (
        Group,
    ) -> Unit,
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
                        onClick = {deleteGroup(group)}
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
//
//@Preview
//@Composable
//fun Preview() {
//    Card(modifier = Modifier
//        .fillMaxWidth()
//        .padding(16.dp)
//        .clickable { }) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Column(
//                    modifier = Modifier.weight(8f),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    DefaultText("Hi!")
//                }
//                Column(
//                    modifier = Modifier.weight(2f),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//
//
//                    IconButton(
//                        onClick = { }
//                    ) {
//                        Icon(
//                            painterResource(id = R.drawable.ic_close_24),
//                            contentDescription = "delete"
//                        )
//                    }
//                }
//            }
//        }
//    }
//}