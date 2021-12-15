package com.example.grosharies.ui.listItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun ListItemNew(
    listId: Int? = null,
    listItemId: Int? = null,
    navController: NavController,
    listItemViewModel: ListItemViewModel
) {
    val currentListItem = listItemViewModel.listItem.value

    val itemName = remember { mutableStateOf(TextFieldValue("")) }
    val itemAmount = remember { mutableStateOf(TextFieldValue("")) }

    itemName.value = TextFieldValue(currentListItem.itemName)
    itemAmount.value = TextFieldValue(currentListItem.itemAmount.toString())

    fun saveListItem(currentListItem: ListItem) {
        listItemViewModel.updateListItem(currentListItem)
        listItemViewModel.getListItemsByListId(listId)
        navController.navigateUp()
    }

    GroshariesTheme {
        setTitle("List Details")
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
                TextField(
                    value = itemName.value,
                    onValueChange = {
                        currentListItem.itemName = it.text
                        itemName.value = it
                    },
                    label = { Text("New item:") },
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
                TextField(
                    value = itemAmount.value,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        currentListItem.itemAmount = it.text.toInt()
                        itemAmount.value = it
                    },
                    label = { Text("amount:") },
                    modifier = Modifier
                        .fillMaxWidth()
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
                onClickListener = { saveListItem(currentListItem) })
        }
    }
}