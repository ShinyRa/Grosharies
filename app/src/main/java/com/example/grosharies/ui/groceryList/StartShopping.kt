package com.example.grosharies.ui.groceryList

import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.common.TextButton
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun StartShopping(GroupId: String? = null, listId: String? = null, navController: NavController) {

    fun finishShopping() {
        navController.navigate("list/view/${GroupId}")
        // TODO: send edited values to the overview
    }

    GroshariesTheme {
        val groceryList =
            GroceryList(
                "Example",
                123,
                "Mikal",
                listOf(
                    ListItem("Item123456789", 1, false),
                    ListItem("Item2", 32, false),
                    ListItem("Item3", 33, false),
                    ListItem("Item4", 362, false),
                    ListItem("Item5", 123, false),
                ),
            1
            )
        val listItems = groceryList.listItems
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn {
                items(listItems.size) { index ->
                    val name = listItems[index].itemName
                    val amount = listItems[index].itemAmount.toString()
                    var bought = listItems[index].itemPurchased

                    val checkedState = remember { mutableStateOf(bought) }
//                    val text = remember { mutableStateOf(checkbox.value) }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    ) {
                        Checkbox(
                            checked = checkedState.value,
                            modifier = Modifier
                                .padding(8.dp),
                            onCheckedChange = {
                                bought = it
                                checkedState.value = it
//                                if (it) {
//                                    showMessage(context, checkbox.value)
//                                }
                            }
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = name,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(4f)
                                )
                                Text(
                                    text = "amount: $amount",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(2f)
                                )
                            }
                        }
                    }
                }
            }
            MainButton(text = "Finish shopping", onClickListener = { finishShopping() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        StartShopping(navController = navController)
    }
}