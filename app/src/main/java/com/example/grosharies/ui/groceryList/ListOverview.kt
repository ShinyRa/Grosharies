package com.example.grosharies.ui.groceryList

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.R
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.GroceryList.GroceryListViewModel.GroceryListViewModelFactory
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun ListOverview(groupId: String, navController: NavController) {

    val context = LocalContext.current
    val myGroceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModelFactory(context.applicationContext as Application)
    )

    if (groupId == "0") {
        myGroceryListViewModel.getListItemsWithoutGroup()
    } else {
        myGroceryListViewModel.getListItemsByGroup(groupId)
    }
    val listItems = myGroceryListViewModel.GroceryLists.observeAsState(listOf()).value

    fun addGroceryList() {
        navController.navigate(Screen.ListNew.withArgs(groupId))
    }

    fun removeFromList(list: GroceryList) {
        myGroceryListViewModel.deleteGroceryLists(list)
    }

    GroshariesTheme {
        val groceryList: List<GroceryList> = listItems
        setTitle("List Overview")

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
                                            .weight(8f)
                                    ) {
                                        Text(text = groceryList[index].listName)
                                        Text(text = "By: ${groceryList[index].createdBy}")
                                        Text(text = "Last edited: ${groceryList[index].lastEdited}")
                                    }
                                    // TODO: get list items
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(2f),
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
                                                        groupId,
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
                                                        groupId,
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        ListOverview("0", navController = navController)
    }
}
