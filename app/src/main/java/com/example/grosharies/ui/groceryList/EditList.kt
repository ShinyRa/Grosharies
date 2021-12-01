package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.ListItem.ListItemViewModel

@Composable
fun EditList(
    listId: String? = null,
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    listItemViewModel: ListItemViewModel,
) {
    if (listId != null) {
        listItemViewModel.getListItemsByList(listId)
    }

    var listItems = listItemViewModel.listItems.observeAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(vertical = 16.dp, horizontal = 8.dp))
            .verticalScroll(state = ScrollState(0))
    ) {
        listItems.value.map { listItem ->
            val itemName = remember { mutableStateOf(TextFieldValue(listItem.itemName)) }
            val itemAmount =
                remember { mutableStateOf(TextFieldValue(listItem.itemAmount.toString())) }
            NewListItem(navController = navController, itemName = itemName, itemAmount = itemAmount)
        }.let {
            val itemName = remember { mutableStateOf(TextFieldValue("")) }
            val itemAmount =
                remember { mutableStateOf(TextFieldValue("")) }
            NewListItem(navController = navController, itemName = itemName, itemAmount = itemAmount)
        }
    }
}
