package com.example.grosharies.ui.listItem

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.setTitle

@Composable
fun ListItemNew(
    listId: Int? = null,
    listItemId: Int? = null,
    navController: NavController,
    listItemViewModel: ListItemViewModel,
) {
    listItemViewModel.getListItemById(listItemId)

    val currentListItem = listItemViewModel.listItem.value

    val itemName = remember { mutableStateOf(TextFieldValue(currentListItem.itemName)) }
    val itemAmount = remember { mutableStateOf(TextFieldValue(currentListItem.itemAmount)) }


    /*
     * update an item if the id's are not null or zero otherwise add a new item
     */
    fun saveListItem(currentListItem: ListItem) {
        if (currentListItem.id == null || currentListItem.id == 0L) {
            currentListItem.listId = listId?.toLong()
            listItemViewModel.insertListItem(currentListItem)
        } else {
            listItemViewModel.updateListItem(currentListItem)
        }
        listItemViewModel.getListItemsByListId(listId)
        navController.navigateUp()
    }

    setTitle("Grocery list item")
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
        )
        {
            DefaultTextInputField(
                value = itemName.value,
                onChange = {
                    currentListItem.itemName = it.text
                    itemName.value = it
                },
                label = "Item",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(
                        PaddingValues(
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 4.dp
                        )
                    )
            )
            DefaultTextInputField(
                value = itemAmount.value,
                onChange = {
                    currentListItem.itemAmount = it.text
                    itemAmount.value = it
                },
                keyboardType = KeyboardType.Number,
                label = "Amount:",
                modifier = Modifier
                    .weight(3f)
                    .padding(
                        PaddingValues(
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 4.dp
                        )
                    )
            )
        }
        MainButton(text = if (listItemId != 0) "Save" else "Create",
            onClickListener = {
                saveListItem(currentListItem)
            })
    }
}