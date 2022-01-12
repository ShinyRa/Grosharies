package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import java.util.*

@Composable
fun NewList(
    groupId: String,
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    nameInputViewModel: NameInputViewModel
) {
    setTitle("New shopping list")
    val listName = remember { mutableStateOf(TextFieldValue("")) }

    // the form for updating the name of the list
    GroshariesTheme {
        Column(Modifier.padding(16.dp)) {
            Column(Modifier.weight(7f)) {
                DefaultTextInputField(
                    value = listName.value,
                    onChange = {
                        listName.value = it
                    },
                    label = "Name",
                )
            }
            Column(Modifier.weight(1f)) {
                MainButton(
                    text = "Create list ",
                    onClickListener = {
                        addNewList(
                            listName.value.text,
                            groupId.toLong(),
                            groceryListViewModel,
                            nameInputViewModel
                        )
                        navController.navigate(Screen.GroupDetail.withArgs(groupId))
                    })
            }
        }
    }
}

/*
 * update the groceryList with the new name and update the "lastEdited" field to the current date
 */
fun addNewList(
    listName: String,
    groupId: Long,
    groceryListViewModel: GroceryListViewModel,
    nameInputViewModel: NameInputViewModel
) {
    nameInputViewModel.getNameInput()
    if (groupId > 0) {
        groceryListViewModel.insertGroceryLists(
            GroceryList(
                listName = listName,
                lastEdited = Date(),
                createdBy = nameInputViewModel.username.value?.name!!,
                groupId = groupId
            )
        )
    } else {
        groceryListViewModel.insertGroceryLists(
            GroceryList(
                listName = listName,
                lastEdited = Date(),
                createdBy = nameInputViewModel.username.value?.name!!
            )
        )
    }
}
