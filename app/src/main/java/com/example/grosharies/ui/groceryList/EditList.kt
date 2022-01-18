package com.example.grosharies.ui.groceryList

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.grosharies.presentation.groceryList.GroceryListViewModel
import com.example.grosharies.ui.common.DefaultTextInputField
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle

@Composable
fun EditList(groupId: String? = null, listId: String, navController: NavController) {
    val context = LocalContext.current
    val groceryListViewModel: GroceryListViewModel = viewModel(
        factory = GroceryListViewModel.GroceryListViewModelFactory(context.applicationContext as Application)
    )
    groceryListViewModel.getGroceryListById(listId.toInt())

    val groceryList = groceryListViewModel.groceryList.value
    val editGroceryName = remember { mutableStateOf(TextFieldValue(groceryList?.listName ?: "")) }

    /*
     * save the edited list and navigate back to the group screen
     */
    fun saveEdit() {
        if (groceryList != null) {
            groceryListViewModel.updateGroceryLists(groceryList)
        }
        navController.navigate(Screen.GroupDetail.withArgs(groupId.toString()))
    }

    setTitle("Edit List")

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(PaddingValues(vertical = 16.dp))) {
            Column(modifier = Modifier.weight(7f)) {
                DefaultTextInputField(
                    label = "Name",
                    value = editGroceryName.value,
                    onChange = {
                        editGroceryName.value = it
                        groceryList?.listName = it.text
                    },
                    modifier = Modifier
                        .padding(
                            PaddingValues(
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 4.dp,
                            )
                        )
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MainButton(text = "Save", onClickListener = { saveEdit() })
            }
        }
    }
}
