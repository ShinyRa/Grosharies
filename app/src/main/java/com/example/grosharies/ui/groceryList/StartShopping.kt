package com.example.grosharies.ui.groceryList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.presentation.listItem.ListItemViewModel
import com.example.grosharies.ui.common.MainButton
import com.example.grosharies.ui.navigation.setTitle
import com.example.grosharies.ui.theme.GroshariesTheme
import com.example.grosharies.ui.theme.SecondaryColor

@Composable
fun StartShopping(
    listItemViewModel: ListItemViewModel,
    navController: NavController
) {

    fun finishShopping(listItems: MutableList<ListItem>) {
        listItems.forEach {listItem ->
            listItemViewModel.updateListItem(listItem = listItem)
        }
        navController.navigateUp()
        // TODO: Think about namespace throughout the app
        // TODO: send edited values to the overview
    }

    GroshariesTheme {
        setTitle("Start Shopping")

        val listItems = listItemViewModel.listItems.value
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            LazyColumn {
                items(listItems.size) { index ->
                    val name = listItems[index].itemName
                    val amount = listItems[index].itemAmount

                    val checkedState = remember { mutableStateOf(listItems[index].itemPurchased) }

                    fun onCheckedChange(it: Boolean) {
                        checkedState.value = !it
                        listItems[index].itemPurchased = !it
                    }

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { onCheckedChange(checkedState.value) }
                    ) {
                        Checkbox(
                            checked = checkedState.value,
                            modifier = Modifier
                                .padding(8.dp),
                            onCheckedChange = { onCheckedChange(checkedState.value) },
                            colors = CheckboxDefaults.colors(checkedColor = SecondaryColor, checkmarkColor = Color.White)
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = name,
                                    style = TextStyle(textDecoration = if (checkedState.value) TextDecoration.LineThrough else TextDecoration.None),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(5f)
                                )
                                Text(
                                    text = "$amount x",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .weight(1f)
                                )
                            }
                        }
                    }
                }
            }
            MainButton(text = "Finish shopping", onClickListener = { finishShopping(listItems) })
        }
    }
}