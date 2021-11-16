package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun ListOverview(groupId: String? = null, navController: NavController) {
    val (lists, setLists) = remember { mutableStateOf(listOf<GroceryList>()) }
    val counter = remember { mutableStateOf(0) }

    fun addGroceryList() {
        counter.value = counter.value + 1
        setLists(
            lists + GroceryList(
                "List ${counter.value}",
                counter.value.toLong(),
                "Name ${counter.value}",
                listOf(
                    ListItem("itemName ${counter.value}", counter.value, false),
                    ListItem("itemName ${counter.value}", counter.value, false),
                    ListItem("itemName ${counter.value}", counter.value, false)
                )
            )
        )
    }

    fun removeFromLists(list: GroceryList) = setLists(lists.filter { it.id != list.id })

    GroshariesTheme {
        val groceryList: List<GroceryList> = getExampleData(groupId)
        Box {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                ) {
                    items(lists.size) { index ->
                        Card(
                            backgroundColor = Color.White,
                            modifier = Modifier.padding(PaddingValues(bottom = 16.dp))
                        ) {
                            Column {
                                Row(
                                    Modifier
                                        .padding(
                                            PaddingValues(
                                                start = 16.dp,
                                                top = 8.dp,
                                                end = 16.dp,
                                            )
                                        )
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                    ) {
                                        Text(text = lists[index].listName)
                                        Text(text = "By: ${lists[index].createdBy}")
                                        Text(text = "Last edited: ${lists[index].lastEdited}")
                                    }
                                    Column(
                                        Modifier
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        lists[index].listItems.take(2).forEach { item ->
                                            Text(text = item.itemName, color = Color.Gray)
                                        }
                                        Text(text = "...", color = Color.Gray)
                                    }
                                }
                                Row(
                                    Modifier
                                        .padding(
                                            PaddingValues(
                                                start = 16.dp,
                                                bottom = 8.dp,
                                                end = 16.dp,
                                            )
                                        )
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                    ) {
                                        TextButton(
                                            text = "Edit",
                                            onClickListener = {
                                                navController.navigate(
                                                    Screen.ListEdit.withArgs(
                                                        groupId.toString(),
                                                        lists[index].id.toString()
                                                    )
                                                )
                                            }
                                        )
                                    }
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                    ) {
                                        MainButton(
                                            text = "Start Shopping",
                                            onClickListener = {
                                                navController.navigate(
                                                    Screen.StartShopping.withArgs(
                                                        groupId.toString(),
                                                        lists[index].id.toString()
                                                    )
                                                )
                                            })
                                    }
                                }
                            }
                        }
                    }
                }
                MainButton(text = "ADD NEW LIST", onClickListener = { addGroceryList() })
            }
        }
    }
}