package com.example.grosharies.ui.groceryList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.Group.Group
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.data.ListItem.ListItemViewModel

@Composable
fun EditList(
    listId: String? = null,
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    listItemViewModel: ListItemViewModel
) {
    if (listId != null) {
        listItemViewModel.getListItemsByList(listId)
    }

    var listItems = listItemViewModel.listItems.observeAsState(initial = listOf())
    listItems.value.map { listItem ->
        val itemName = remember { mutableStateOf(TextFieldValue(listItem.itemName)) }
        val itemAmount = remember { mutableStateOf(TextFieldValue(listItem.itemAmount.toString())) }
        NewListItem(navController = navController, itemName = itemName, itemAmount = itemAmount)
//        listItem.itemName = itemName.toString()
//        listItem.itemAmount = itemAmount.toString().toInt()
    }

}
