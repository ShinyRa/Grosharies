package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.presentation.nameInput.NameInputViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
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
    var listName by remember { mutableStateOf("") }

    GroshariesTheme {
        Column {
            Row(
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            start = 16.dp,
                            end = 16.dp,
                        )
                    )
                    .fillMaxWidth(),
            ) {
                TextField(
                    value = listName,
                    onValueChange = {
                        if (!it.contains("\n"))
                            listName = it
                    },
                    label = { Text("New list name:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            PaddingValues(
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 4.dp
                            )
                        )
                )
            }
            MainButton(
                text = "ADD NEW LIST",
                onClickListener = {
                    addNewList(listName, groupId.toLong(), groceryListViewModel, nameInputViewModel)
                    navController.navigate(Screen.GroupDetail.withArgs(groupId))
                })
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
