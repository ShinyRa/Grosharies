package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.presentation.listItem.ListItemViewModel
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
    listItemViewModel: ListItemViewModel,
    nameInputViewModel: NameInputViewModel
) {
    setTitle("New shopping list")
    val listName = remember { mutableStateOf(TextFieldValue("")) }


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
                        addNewList(listName.value.text,
                            groupId.toLong(),
                            groceryListViewModel,
                            nameInputViewModel)
                        navController.navigate(Screen.GroupDetail.withArgs(groupId))
                    })
            }
        }
    }
}

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
