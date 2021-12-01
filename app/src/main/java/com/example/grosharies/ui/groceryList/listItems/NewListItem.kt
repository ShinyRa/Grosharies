package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun NewListItem(
    navController: NavController,
    itemName: MutableState<TextFieldValue>,
    itemAmount: MutableState<TextFieldValue>
) {
//    fun submit(listItem: ListItem) {
//        if (listId != null) {
//            listItemViewModel.updateListItem(listItem)
//        } else {
//            listItemViewModel.insertListItem(listItem)
//        }
//
//        navController.navigateUp()
//    }
//
//    if (listId != null) {
//        listItemViewModel.getListItemsByList(listId)
//    }
    GroshariesTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.padding(PaddingValues(vertical = 16.dp))) {
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
                        value = itemAmount,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            itemAmount = it
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
            }
        }

    }
}