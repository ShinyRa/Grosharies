package com.example.grosharies.ui.groceryList

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.R
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.presentation.groceryList.GroceryListViewModel
import com.example.grosharies.presentation.groceryList.GroceryListViewModel.GroceryListViewModelFactory
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.TopBarAction
import com.example.grosharies.ui.navigation.setActions
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.backdrop
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListOverview(groupId: String, navController: NavController, listItemViewModel: ListItemViewModel) {

    val context = LocalContext.current
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModelFactory(context.applicationContext as Application)
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

    fun addGroceryList() {
        navController.navigate(Screen.ListNew.withArgs(groupId))
    }

    fun removeFromList(list: GroceryList) {
        groceryListViewModel.deleteGroceryLists(list)
        groceryListViewModel.deleteGroceryLists(list)
    }

    /*
     * Set the title to the group name if a group was found, otherwise to My lists
     */
    setTitle(if (groupId != "0") group?.name else "My lists")

    /*
     * Add an edit group action to topbar if route matches ListOverview and a groupId exists
     */
    navController.addOnDestinationChangedListener { _, destination, _ ->
        if (destination.route!!.contains(Screen.Lists.route) && groupId != "0") {
            setActions(listOf(TopBarAction(R.drawable.ic_edit_24) {
                navController.navigate(Screen.GroupEdit.withArgs(groupId))
            }))
        } else {
            setActions(listOf())
        }
    }

    val listItems = groceryListViewModel.GroceryLists.observeAsState(listOf()).value
    val groceryList: List<GroceryList> = listItems

    GroshariesTheme {
        Surface(color = backdrop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                LazyColumn(
                    modifier = Modifier.weight(7f).padding(vertical = 10.dp, horizontal = 8.dp)
                ) {
                    items(groceryList.size) { index ->
                        Card(
                            backgroundColor = Color.White,
                            modifier = Modifier
                                .padding(16.dp)
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
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(8f)
                                    ) {
                                        Text(text = groceryList[index].listName, fontWeight = FontWeight.Bold)
                                        Text(text = "Updated on: ${groceryList[index].lastEdited}")
                                        Text(text = "By: ${groceryList[index].createdBy}", fontSize = 14.sp)
                                    }
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
                            }
                        }
                    }
                }
                Column(modifier = Modifier.weight(1f).padding(vertical = 16.dp)) {
                    RoundedButton(text = "Create new list", onClickListener = { addGroceryList() })
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