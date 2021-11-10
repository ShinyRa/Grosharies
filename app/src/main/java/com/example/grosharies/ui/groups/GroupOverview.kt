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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.ui.common.DefaultText
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@Composable
fun GroupOverview(navController: NavController) {
    val (groups, setGroups) = remember {
        mutableStateOf(
            listOf<Group>(
                Group(-1, "Test"),
                Group(-2, "Test 2")
            )
        )
    }
    val counter = remember { mutableStateOf(0) }

    fun addGroup() {
        counter.value = counter.value + 1
        setGroups(groups + Group(counter.value, "Group ${counter.value}"))
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
                    .padding(16.dp)
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
                    RoundedButton(text = "Join", isSecondary = true, onClickListener = { })
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