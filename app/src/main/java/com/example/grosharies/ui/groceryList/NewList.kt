package com.example.grosharies.ui.groceryList

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme
import java.util.*



@Composable
fun NewList(groupId: String, navController: NavController) {
    var listName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val myGroceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )

    GroshariesTheme {
        Column {
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
                    value = listName,
                    onValueChange = {
                        listName = it
                    },
                    label = { Text("New item name:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            PaddingValues(
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 4.dp
                            )
                        )
                )
            }
            MainButton(
                text = "ADD NEW LIST",
                onClickListener = {
                    myGroceryListViewModel.insertGroceryLists(
                        GroceryList(
                            listName = listName,
                            lastEdited = Date(),
                            createdBy = "Mikal",
                            groupId = groupId.toLong()
                        )
                    )
                    navController.navigate(Screen.GroupDetail.withArgs(groupId))
                })
        }
    }
}