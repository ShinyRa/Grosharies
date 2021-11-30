package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme
import java.util.*

lateinit var groceryListViewModel: GroceryListViewModel
lateinit var listItemViewModel: ListItemViewModel

@Composable
fun NewList(groupId: String, navController: NavController) {
    var listName by remember { mutableStateOf("") }
    var itemName by remember { mutableStateOf("") }
    var itemAmount by remember { mutableStateOf("") }
    val context = LocalContext.current

    groceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(
            context.applicationContext as android.app.Application
        )
    )

    listItemViewModel = viewModel(
        factory = ListItemViewModel.ListItemViewModelFactory(
            context.applicationContext as android.app.Application
        )
    )

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
                    value = itemName,
                    onValueChange = {
                        itemName = it
                    },
                    label = { Text("New item:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(8f)
                        .padding(
                            PaddingValues(
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 4.dp
                            )
                        )
                )
                TextField(
                    value = itemAmount,
                    onValueChange = {
                        itemAmount = it
                    },
                    label = { Text("amount:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
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
                    addNewList(listName, groupId.toLong())
//                    addNewListItem(itemName, itemAmount.toInt(), 40)
                    navController.navigate(Screen.GroupDetail.withArgs(groupId))
                })
        }
    }
}

fun addNewList(listName: String, groupId: Long) {
    if (groupId > 0) {
        groceryListViewModel.insertGroceryLists(
            GroceryList(
                listName = listName,
                lastEdited = Date(),
                createdBy = "Mikal",
                groupId = groupId
            )
        )
    } else {
        groceryListViewModel.insertGroceryLists(
            GroceryList(
                listName = listName,
                lastEdited = Date(),
                createdBy = "Mikal"
            )
        )
    }
}

fun addNewListItem(listItemName: String, itemAmount: Int, listId: Long) {
    listItemViewModel.insertListItem(
        ListItem(
            itemName = listItemName,
            itemAmount = itemAmount,
            itemPurchased = false,
            listId = listId
        )
    )
}