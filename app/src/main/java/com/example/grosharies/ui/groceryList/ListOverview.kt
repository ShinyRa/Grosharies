package com.example.grosharies.ui.groceryList

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
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
import java.util.*
import com.github.marlonlom.utilities.timeago.TimeAgo

import com.github.marlonlom.utilities.timeago.TimeAgoMessages




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
    val groceryLists: List<GroceryList> = listItems

    GroshariesTheme {
        Surface(color = backdrop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .verticalScroll(ScrollState(0))
                ) {
                    groceryLists.map { groceryList ->
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    listCardClicked(
                                        navController,
                                        listItemViewModel,
                                        groceryList.id.toString()
                                    )
                                }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(8f)
                                    ) {
                                        Text(text = groceryList.listName, fontWeight = FontWeight.Bold)
                                        Text(text = "${humanReadableDuration(groceryList.lastEdited.time)}", modifier = Modifier.padding(top = 8.dp, bottom = 2.dp))
                                        Text(text = "By: ${groceryList.createdBy}", fontSize = 12.sp)
                                    }
                                    Column(
                                        Modifier
                                            .padding(8.dp)
                                            .weight(2f),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        IconButton(onClick = { removeFromList(groceryList) }) {
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

                    Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier
                        .padding(vertical = 16.dp)
                        .weight(1f)) {
                        RoundedButton(text = "Create new shopping list", onClickListener = { addGroceryList() })
                    }
                }
            }
        }
    }
}

fun listCardClicked(
    navController: NavController,
    listItemViewModel: ListItemViewModel,
    listId: String,
) {
    listItemViewModel.getListItemsByListId(listId.toInt())
    navController.navigate(
        Screen.ListDetail.withArgs(listId)
    )
}

fun humanReadableDuration(time: Long): String {
    val LocaleBylanguageTag: Locale = Locale.forLanguageTag("en")
    val messages: TimeAgoMessages = TimeAgoMessages.Builder().withLocale(LocaleBylanguageTag).build()

    return TimeAgo.using(time, messages)
}