package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.GroceryList.GroceryListViewModel
import com.example.grosharies.data.ListItem.ListItemViewModel
import com.example.grosharies.ui.navigation.Screen
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme

@Composable
fun EditList(
    listId: String? = null,
    navController: NavController,
    groceryListViewModel: GroceryListViewModel,
    listItemViewModel: ListItemViewModel,
) {
    if (listId != null) {
        listItemViewModel.getListItemsByList(listId)
    }

    var listItems = listItemViewModel.listItems.observeAsState(initial = listOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(vertical = 16.dp, horizontal = 8.dp))
            .verticalScroll(state = ScrollState(0))
    ) {
        listItems.value.map { listItem ->
            val itemName = remember { mutableStateOf(TextFieldValue(listItem.itemName)) }
            val itemAmount =
                remember { mutableStateOf(TextFieldValue(listItem.itemAmount.toString())) }
            NewListItem(navController = navController, itemName = itemName, itemAmount = itemAmount)
        }.let {
            val itemName = remember { mutableStateOf(TextFieldValue("")) }
            val itemAmount =
                remember { mutableStateOf(TextFieldValue("")) }
            NewListItem(navController = navController, itemName = itemName, itemAmount = itemAmount)
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
