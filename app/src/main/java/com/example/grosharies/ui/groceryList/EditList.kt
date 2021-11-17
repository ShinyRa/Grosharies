package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun EditList(groupId: String? = null, listId: String? = null, navController: NavController) {

    fun SaveEdit() {
        navController.navigate(Screen.GroupDetail.withArgs(groupId.toString()))

        // TODO: send edited values to the overview
    }


    GroshariesTheme {
        val groceryList =
            GroceryList(
                "Example",
                123,
                "Mikal",
                listOf(
                    ListItem("Item1", 1, false),
                    ListItem("Item2", 32, false),
                    ListItem("Item3", 33, false),
                    ListItem("Item4", 362, false),
                    ListItem("Item5", 123, false),
                    ListItem("Item6", 33, false),
                )
            )
        val listItems = groceryList.listItems
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn {
                items(listItems.size) { index ->
                    var name = listItems[index].itemName
                    var amount = listItems[index].itemAmount
                    Row(
                        modifier = Modifier
                            .padding(
                                PaddingValues(
                                    start = 16.dp,
                                    top = 8.dp,
                                    end = 16.dp,
                                )
                            )
                            .fillMaxWidth(),
                    ) {
                        TextField(
                            value = listItems[index].itemName,
                            onValueChange = { name = it },
                            label = { Text("Item name:") },
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    PaddingValues(
                                        top = 4.dp,
                                        bottom = 4.dp,
                                        end = 4.dp
                                    )
                                )
                        )
                        TextField(
                            value = listItems[index].itemAmount.toString(),
                            onValueChange = {
                                amount = it.toInt()
                            },
                            label = { Text("Amount:") },
                            modifier = Modifier
                                .weight(2f)
                                .padding(
                                    PaddingValues(
                                        top = 4.dp,
                                        bottom = 4.dp,
                                        start = 4.dp
                                    )
                                )
                        )
                    }
                }
            }
            var newName: String
            var newAmount: Int

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
                        value = "",
                        onValueChange = { newName = it },
                        label = { Text("New item name:") },
                        modifier = Modifier
                            .weight(5f)
                            .padding(
                                PaddingValues(
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    end = 4.dp
                                )
                            )
                    )
                    TextField(
                        value = "",
                        onValueChange = {
                            newAmount = it.toInt()
                        },
                        label = { Text("Amount:") },
                        modifier = Modifier
                            .weight(2f)
                            .padding(
                                PaddingValues(
                                    top = 8.dp,
                                    bottom = 8.dp,
                                    start = 4.dp
                                )
                            )
                    )
                }
                MainButton(text = "SAVE", onClickListener = { SaveEdit() })

//                Button(
//                    onClick = { finishShopping() }, modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                ) {
//                    Text(text = "SAVE")
//                }
            }
        }
    }
}