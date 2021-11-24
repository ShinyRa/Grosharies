package com.example.grosharies.ui.groceryList

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.theme.GroshariesTheme
import java.util.*

@Composable
fun StartShopping(groupId: String? = null, listId: String? = null, navController: NavController) {

    fun finishShopping() {
        navController.navigate(Screen.GroupDetail.withArgs(groupId.toString()))
        // TODO: Think about namespace throughout the app
        // TODO: send edited values to the overview
    }

    GroshariesTheme {
        val groceryList =
            GroceryList(
                "Example",
                Date(),
                "Mikal",

                1
            )
//        val listItems = groceryList.listItems
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceBetween,
//        ) {
//            LazyColumn {
//                items(listItems.size) { index ->
//                    val name = listItems[index].itemName
//                    val amount = listItems[index].itemAmount.toString()
//
//                    val checkedState = remember { mutableStateOf(listItems[index].itemPurchased) }
//
//                    Row(
//                        modifier = Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth(),
//                    ) {
//                        Checkbox(
//                            checked = checkedState.value,
//                            modifier = Modifier
//                                .padding(8.dp),
//                            onCheckedChange = {
//                                checkedState.value = it
//                            }
//                        )
//                        Column(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Row(modifier = Modifier.fillMaxWidth()) {
//                                Text(
//                                    text = name,
//                                    modifier = Modifier
//                                        .padding(8.dp)
//                                        .weight(4f)
//                                )
//                                Text(
//                                    text = "amount: $amount",
//                                    modifier = Modifier
//                                        .padding(8.dp)
//                                        .weight(2f)
//                                )
//                            }
//                        }
//                    }
//                }
//            }
        MainButton(text = "Finish shopping", onClickListener = { finishShopping() })
    }
}