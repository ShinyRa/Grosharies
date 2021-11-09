package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CreateListOverview(
    groupId: String? = null,
    modifier: Modifier = Modifier
) {
    val groceryList: List<GroceryList> = getExampleData(groupId)
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 8.dp
            )
        ) {
            items(groceryList.size) { index ->
                Card(
                    backgroundColor = Color.White,
                    modifier = Modifier.padding(PaddingValues(bottom = 16.dp))
                ) {
                    Column{
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
                                Text(text = groceryList[index].listName)
                                Text(text = "By: ${groceryList[index].createdBy}")
                                Text(text = "Last edited: ${groceryList[index].lastEdited}")
                            }
                            Column(
                                Modifier
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                groceryList[index].listItems.take(2).forEach { item ->
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
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Edit")
                                }
                            }
                            Column(
                                Modifier
                                    .padding(8.dp)
                            ) {
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Start shopping")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}