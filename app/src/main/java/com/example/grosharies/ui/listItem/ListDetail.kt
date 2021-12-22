package com.example.grosharies.ui.listItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun ListDetail(listId: String, navController: NavController, listItemViewModel: ListItemViewModel) {
    listItemViewModel.getListItemsByListId(listId.toInt())
    val listItems = listItemViewModel.listItems.value

    fun addListItem() {
        navController.navigate(Screen.ListItemNew.withArgs(listId, "0"))
    }

    fun startShopping() {
        navController.navigate(Screen.StartShopping.route)
    }

    fun deleteListItem(listItem: ListItem) {
        listItemViewModel.deleteListItem(listItem)
        listItemViewModel.getListItemsByListId(listId.toInt())
    }

    fun editListItem(listItemId: Int) {
        listItemViewModel.getListItemById(listItemId)
        navController.navigate(Screen.ListItemNew.withArgs(listId, listItemId.toString()))
    }

    GroshariesTheme {
        val listItemsList: List<ListItem> = listItems
        setTitle("Shopping list")

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
                    items(listItemsList.size) { index ->
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
                                    .fillMaxWidth()
                                    .clickable { editListItem(listItemsList[index].id?.toInt()!!) },
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(3f),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        if (listItemsList[index].itemPurchased) {
                                            Text(
                                                text = listItemsList[index].itemName,
                                                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                            )
                                        } else {
                                            Text(
                                                text = listItemsList[index].itemName,
                                            )
                                        }

                                    }
                                    Column(
                                        Modifier
                                            .weight(2f),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "${listItemsList[index].itemAmount} x"
                                        )
                                    }
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(1f),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Row {
                                            IconButton(onClick = { deleteListItem(listItemsList[index]) }) {
                                                Icon(
                                                    painterResource(id = R.drawable.ic_close_24),
                                                    contentDescription = "Delete"
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                MainButton(text = "Add item", onClickListener = {
                    addListItem()
                })
                MainButton(text = "Start shopping", onClickListener = {
                    startShopping()
                })
            }
        }
    }
}