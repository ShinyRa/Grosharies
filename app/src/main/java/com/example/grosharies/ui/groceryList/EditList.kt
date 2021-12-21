package com.example.grosharies.ui.groceryList

import android.app.Application
import android.util.Log
import androidx.compose.material.TextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.Group.GroupViewModel
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.common.DefaultText
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun EditList(groupId: String? = null, listId: String, navController: NavController) {
    val context = LocalContext.current
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )

    groceryListViewModel.getGroceryListById(listId.toInt())

    var groceryList = groceryListViewModel.groceryList.value

    var newAmount = 0
    var newName = ""
    var editGroceryname = remember { mutableStateOf(groceryList?.listName ?: "")}

    fun SaveEdit() {
        if (groceryList != null) {
            groceryListViewModel.updateGroceryLists(groceryList)
        }
        navController.navigate(Screen.GroupDetail.withArgs(groupId.toString()))
    }

    GroshariesTheme {
        setTitle("Edit List")

        Column {
            TextField(
                value = editGroceryname.value,
                onValueChange = {
                    editGroceryname.value = it
                    groceryList?.listName = it
                },
                label = { Text ( " Edit the name of your list :") },
                modifier = Modifier
                    .weight(5f)
                    .padding(
                        PaddingValues(
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 4.dp,
                        )
                    )
            )

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
            ClickableText(
                text = AnnotatedString("Add new row"),
                onClick = {
                    // TODO: figure out how to add a new row
                },
                modifier = Modifier.padding(PaddingValues(start = 16.dp))
            )
            MainButton(text = "Save", onClickListener = { SaveEdit()})

        }
    }
}
