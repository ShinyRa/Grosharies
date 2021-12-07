package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.Group.Group
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.ui.common.MainButton

@Composable
fun New(
    navController: NavController,
    groupViewModel: GroupViewModel,
    groupId: Int? = null,
) {
    fun submit(group: Group) {
        if (groupId != null) {
            groupViewModel.updateGroup(group)
        } else {
            groupViewModel.insertGroup(group)
        }

        navController.navigateUp()
    }


    groupViewModel.getGroupById(groupId)

    var group = groupViewModel.group.value

    var name = remember { mutableStateOf(TextFieldValue("")) }
    var password = remember { mutableStateOf(TextFieldValue("")) }

    if (group != null) {
        name.value = TextFieldValue(group.name)
        password.value = TextFieldValue(group.id.toString())
    }

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(PaddingValues(vertical = 16.dp))) {
            Text(text = "name",
                modifier = Modifier.padding(PaddingValues(horizontal = 7.dp)),
                fontWeight = FontWeight.Medium)
            TextField(value = name.value,
                onValueChange = {
                    name.value = it
                    group.name = it.text
                },
                modifier = Modifier.fillMaxWidth())
            Text(text = "password",
                modifier = Modifier.padding(PaddingValues(horizontal = 7.dp)),
                fontWeight = FontWeight.Medium)
            TextField(value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier.fillMaxWidth())
            MainButton(text = if (groupId != null) "Save" else "Create",
                onClickListener = { submit(group) })
        }
    }
}