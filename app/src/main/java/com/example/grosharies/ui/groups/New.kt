package com.example.grosharies.ui.groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.group.Group
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
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

    name.value = TextFieldValue(group.name)

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(PaddingValues(vertical = 16.dp))) {
            Column(modifier = Modifier.weight(7f)) {
                DefaultTextInputField(
                    value = name.value,
                    label = "Name",
                    onChange = {
                        name.value = it
                        group.name = it.text
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MainButton(text = if (groupId != null) "Save" else "Create",
                    onClickListener = { submit(group) })
            }
        }
    }
}