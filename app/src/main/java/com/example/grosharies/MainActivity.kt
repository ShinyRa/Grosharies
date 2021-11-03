package com.example.grosharies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.ListItem.ListItem
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.background2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroshariesTheme {
                Surface(color = background2) {
                    CreateListOverview(getExampleData())
                }
            }
        }
    }
}

fun getExampleData(): List<GroceryList> {
    return listOf(
        GroceryList(
            "test1", 123, "Mikal",
            listOf(
                ListItem("test", 1, false),
                ListItem("test2", 2, false)
            ),
        ),
        GroceryList(
            "test2", 123, "Mikal",
            listOf(
                ListItem("test", 1, false),
                ListItem("test2", 2, false),
                ListItem("test3", 2, false),
                ListItem("test4", 2, false),
            ),
        ),
        GroceryList(
            "test3", 123456789, "Mikal",
            listOf(
                ListItem("test", 1, false)
            ),
        )
    )
}

@Composable
fun CreateListOverview(
    groceryList: List<GroceryList>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 8.dp
            )
        ) {
            items(groceryList.size) { index ->
                Card(
                    backgroundColor = White,
                    modifier = Modifier.padding(PaddingValues(bottom = 16.dp))
                ) {
                    Column() {
                        Row(
                            Modifier
                                .padding(
                                    PaddingValues(
                                        start = 16.dp,
                                        top = 8.dp,
                                        end = 16.dp,
                                    )
                                )
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                Modifier
                                    .padding(8.dp)
                            ) {
                                Text(text = groceryList[index].listName)
                                Text(text = "By: ${groceryList[index].createdBy}")
                                Text(text = "Last edited: ${groceryList[index].lastEdited}")
                            }
                            Column(
                                Modifier
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                groceryList[index].listItems.take(2).forEach { item ->
                                    Text(text = item.itemName, color = Gray)
                                }
                                Text(text = "...", color = Gray)
                            }
                        }
                        Row(
                            Modifier
                                .padding(
                                    PaddingValues(
                                        start = 16.dp,
                                        bottom = 8.dp,
                                        end = 16.dp,
                                    )
                                )
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                Modifier
                                    .padding(8.dp)
                            ) {
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Edit")
                                }
                            }
                            Column(
                                Modifier
                                    .padding(8.dp)
                            ) {
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = "Start shopping")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GroshariesTheme {
        Surface(color = background2) {
            CreateListOverview(getExampleData())
        }
    }
}