package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun EditList(groupId: String? = null, listId: String? = null, navController: NavController) {
//    val viewModel: ListItemViewModel

    var newAmount = 0
    var newName = ""

    fun SaveEdit() {

//        if (newAmount != 0 && newName.isNotEmpty()) {
//            viewModel.insertGroceryLists()
//        }

        navController.navigate(Screen.GroupDetail.withArgs(groupId.toString()))
        // TODO: send edited values to the overview
    }

    GroshariesTheme {
//        val listItems = ListItemViewModel.get
//        Column(
//            modifier = Modifier
//                .fillMaxHeight(),
//            verticalArrangement = Arrangement.SpaceBetween,
//        ) {
//            LazyColumn {
//                items(listItems.size) { index ->
//                    var name = listItems[index].itemName
//                    var amount = listItems[index].itemAmount
//                    Row(
//                        modifier = Modifier
//                            .padding(
//                                PaddingValues(
//                                    start = 16.dp,
//                                    top = 8.dp,
//                                    end = 16.dp,
//                                )
//                            )
//                            .fillMaxWidth(),
//                    ) {
//                        TextField(
//                            value = listItems[index].itemName,
//                            onValueChange = { name = it },
//                            label = { Text("Item name:") },
//                            modifier = Modifier
//                                .weight(5f)
//                                .padding(
//                                    PaddingValues(
//                                        top = 4.dp,
//                                        bottom = 4.dp,
//                                        end = 4.dp
//                                    )
//                                )
//                        )
//                        TextField(
//                            value = listItems[index].itemAmount.toString(),
//                            onValueChange = {
//                                amount = it.toInt()
//                            },
//                            label = { Text("Amount:") },
//                            modifier = Modifier
//                                .weight(2f)
//                                .padding(
//                                    PaddingValues(
//                                        top = 4.dp,
//                                        bottom = 4.dp,
//                                        start = 4.dp
//                                    )
//                                )
//                        )
//                    }
//                }
//            }
        setTitle("Edit List")

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
            Button(
                onClick = { SaveEdit() }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "SAVE")
            }
        }
    }
}
