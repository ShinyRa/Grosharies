package com.example.grosharies.ui.groceryList

import android.app.Application
import androidx.compose.foundation.clickable
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
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.TopBarAction
import com.example.grosharies.ui.navigation.setActions
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop

@Composable
fun ListOverview(groupId: String, navController: NavController, listItemViewModel: ListItemViewModel) {

    val context = LocalContext.current
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )

    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModel.GroupViewModelFactory(context.applicationContext as Application)
    )

    if (groupId == "0") {
        groceryListViewModel.getListItemsWithoutGroup()
    } else {
        groupViewModel.getGroupById(groupId.toInt())
        groceryListViewModel.getListItemsByGroup(groupId)
    }

    val group = groupViewModel.group.value

    val listItems = groceryListViewModel.GroceryLists.observeAsState(listOf()).value

    fun addGroceryList() {
        navController.navigate(Screen.ListNew.withArgs(groupId))
    }

    fun removeFromList(list: GroceryList) {
        groceryListViewModel.deleteGroceryLists(list)
        groceryListViewModel.deleteGroceryLists(list)
    }

    setTitle(if (groupId != "0") group?.name else "Personal lists")


    navController.addOnDestinationChangedListener { _, destination, _ ->
        if (destination.route!!.contains(Screen.Lists.route)) {
            if (groupId != "0") {
                setActions(listOf(TopBarAction(R.drawable.ic_edit_24) {
                    navController.navigate(Screen.GroupEdit.withArgs(groupId))
                }))
            } else {
                setActions(listOf())
            }
        } else {
            setActions(listOf())
        }
    }

        val groceryList: List<GroceryList> = listItems
    GroshariesTheme {
        Surface(color = backdrop,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
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
                                modifier = Modifier
                                    .padding(PaddingValues(bottom = 16.dp))
                                    .clickable {
                                        listCardClicked(
                                            navController,
                                            listItemViewModel,
                                            groceryList[index].id.toString()
                                        )
                                    }
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
                                                }
                                            )

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun listCardClicked(
    navController: NavController,
    listItemViewModel: ListItemViewModel,
    listId: String
) {
    listItemViewModel.getListItemsByListId(listId.toInt())
    navController.navigate(
        Screen.ListDetail.withArgs(listId)
    )
}
