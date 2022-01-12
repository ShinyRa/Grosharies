package com.example.grosharies.ui.groceryList

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.R
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.presentation.groceryList.GroceryListViewModel
import com.example.grosharies.presentation.group.GroupViewModel
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.RoundedButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.TopBarAction
import com.example.grosharies.ui.navigation.setActions
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.backdrop
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import java.util.*

@Composable
fun ListOverview(
    groupId: String,
    navController: NavController,
    listItemViewModel: ListItemViewModel,
) {

    val context = LocalContext.current
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )

    val groupViewModel: GroupViewModel = viewModel(
        factory = GroupViewModel.GroupViewModelFactory(context.applicationContext as Application)
    )

    /*
     * Get the grocery personal groceryLists if the groupId is zero, otherwise get the groceryLists
     * from a certain group
     */
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
    setTitle(if (groupId != "0") group.name else "My lists")

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

    val listItems = groceryListViewModel.groceryLists.observeAsState(listOf()).value
    val groceryLists: List<GroceryList> = listItems

    Surface(
        color = backdrop,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            // check if the empty state should be shown
            if (groceryLists.isEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (groupId != "0") "There are no lists here yet!" else "You don't have any lists yet!",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = if (groupId != "0") "Be the first to create a shopping list here" else "Create one to start shopping",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.per_im),
                        contentDescription = "No lists yet!",
                        modifier = Modifier.size(300.dp, 300.dp)
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .verticalScroll(ScrollState(0))
                ) {
                    // map through the groceryLists to show them individually in cards
                    groceryLists.map { groceryList ->
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    listCardClicked(
                                        navController,
                                        listItemViewModel,
                                        groupId,
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
                                        Text(
                                            text = groceryList.listName,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = humanReadableDuration(groceryList.lastEdited.time),
                                            modifier = Modifier.padding(
                                                top = 8.dp,
                                                bottom = 2.dp
                                            )
                                        )
                                        Text(
                                            text = "By: ${groceryList.createdBy}",
                                            fontSize = 12.sp
                                        )
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
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom, modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(6f)
            ) {
                RoundedButton(text = "Create new shopping list",
                    onClickListener = { addGroceryList() })
            }
        }
    }
}

/*
 * navigate to the detail view when the card is clicked
 */
fun listCardClicked(
    navController: NavController,
    listItemViewModel: ListItemViewModel,
    groupId: String,
    listId: String,
) {
    listItemViewModel.getListItemsByListId(listId.toInt())
    navController.navigate(
        Screen.ListDetail.withArgs(groupId, listId)
    )
}

/*
 * convert a date to text like "an hour ago"
 */
fun humanReadableDuration(time: Long): String {
    val localeBylanguageTag: Locale = Locale.forLanguageTag("en")
    val messages: TimeAgoMessages =
        TimeAgoMessages.Builder().withLocale(localeBylanguageTag).build()

    return TimeAgo.using(time, messages)
}