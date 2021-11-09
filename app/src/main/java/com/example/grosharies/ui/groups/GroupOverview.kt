package com.example.grosharies.ui.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@Composable
fun GroupOverview(navController: NavController) {
    val (groups, setGroups) = remember { mutableStateOf(listOf<Group>()) }
    val counter = remember { mutableStateOf(0) }

    fun addGroup() {
        counter.value = counter.value + 1
        setGroups(groups + Group(counter.value, "Group ${counter.value}"))
    }

    fun removeFromGroups(group: Group) = setGroups(groups.filter { it.id != group.id })

    GroshariesTheme {
        Surface(color = backdrop, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column {
                groups.map { group ->
                    GroupCard(
                        group = group,
                        onClick = { navController.navigate("group/view/${group.id}") },
                        deleteGroup = { group -> removeFromGroups(group) }
                    )
                }

                Button(onClick = { addGroup() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "ADD GROUP")
                }
            }
        }
    }
}

@Composable
fun GroupCard(group: Group, onClick: () -> Unit, deleteGroup: (Group) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Column {
                    Text("ID: " + group.id.toString())
                    Text("Name: " + group.name)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
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