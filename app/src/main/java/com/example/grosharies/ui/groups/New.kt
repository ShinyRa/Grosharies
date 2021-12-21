package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.group.Group
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.setTitle

@Composable
fun New(
    navController: NavController,
    groupViewModel: GroupViewModel,
    groupId: Int? = null,
) {
    setTitle(if (groupId != null) "Edit group" else "Create a group")

    fun submit(group: Group) {
        if (groupId != null) {
            groupViewModel.updateGroup(group)
        } else {
            groupViewModel.insertGroup(group)
        }

        navController.navigateUp()
    }


    groupViewModel.getGroupById(groupId)

    val group = groupViewModel.group.value

    val name = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }

    name.value = TextFieldValue(group.name)
    password.value = TextFieldValue("")

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(PaddingValues(vertical = 16.dp))) {
            Text(
                text = "name",
                modifier = Modifier.padding(PaddingValues(horizontal = 7.dp)),
                fontWeight = FontWeight.Medium
            )
            TextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                    group.name = it.text
                },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "password",
                modifier = Modifier.padding(PaddingValues(horizontal = 7.dp)),
                fontWeight = FontWeight.Medium
            )
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier.fillMaxWidth()
            )
            MainButton(text = if (groupId != null) "Save" else "Create",
                onClickListener = { submit(group) })
        }
    }
}