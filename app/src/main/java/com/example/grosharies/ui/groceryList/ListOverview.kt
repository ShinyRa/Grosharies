package com.example.grosharies.ui.groceryList

import android.app.Application
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.GroceryList.GroceryListViewModel.GroceryListViewModelFactory
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun ListOverview(groupId: String, navController: NavController) {
    val (lists, setLists) = remember { mutableStateOf(listOf<GroceryList>()) }

    val context = LocalContext.current
    val myGroceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModelFactory(context.applicationContext as Application)
    )

//    val listItems = myGroceryListViewModel.getAllGroceryLists.observeAsState(listOf()).value
    myGroceryListViewModel.getListItemsByGroup(groupId)
    val listItems = myGroceryListViewModel.listItems.observeAsState(listOf()).value

    fun addGroceryList() {
        myGroceryListViewModel.insertGroceryLists(
            GroceryList(
                "test", 123, "Mikal", groupId = groupId.toLong(),
            )
        )
    }

    fun removeFromLists(list: GroceryList) = setLists(lists.filter { it.id != list.id })

    fun removeFromList(list: GroceryList) {
        myGroceryListViewModel.deleteGroceryLists(list)
    }

    GroshariesTheme {
        val groceryList: List<GroceryList> = listItems

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
                    items(groceryList.size) { index ->
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
                                        Text(text = groceryList[index].listName)
                                        Text(text = "By: ${groceryList[index].createdBy}")
                                        Text(text = "Last edited: ${groceryList[index].lastEdited}")
                                    }
                                    // TODO: get list items
                                    Column(
                                        Modifier
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.End
                                    ) {
//                                        groceryList[index].listItems.take(2).forEach { item ->
//                                            Text(text = item.itemName, color = Color.Gray)
//                                        }
//                                        Text(text = "...", color = Color.Gray)
                                        IconButton(onClick = { removeFromList(groceryList[index]) }) {
                                            Icon(
                                                painterResource(id = R.drawable.ic_close_24),
                                                contentDescription = "close"
                                            )
                                        }
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
                                                        groceryList[index].id.toString()
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
                                                        groceryList[index].id.toString()
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