package com.example.grosharies.data.GroceryList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grosharies.data.ListItem.ListItem

@Entity(tableName = "list")
data class GroceryList(

    @ColumnInfo(name = "listName")
    val listName: String,
    @ColumnInfo(name = "lastEdited")
    val lastEdited: Long,
    @ColumnInfo(name = "createdBy")
    val createdBy: String,
    @ColumnInfo(name = "listItems")
    val listItems: List<ListItem>,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
) {
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