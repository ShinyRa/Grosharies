package com.example.grosharies.data.GroceryList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grosharies.data.ListItem.ListItem

@Entity(tableName = "GroceryList")
data class GroceryList(

    @ColumnInfo(name = "listName")
    val listName: String,
    @ColumnInfo(name = "lastEdited")
    val lastEdited: Long,
    @ColumnInfo(name = "createdBy")
    val createdBy: String,
    @ColumnInfo(name = "groupId")
    val groupId: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)

fun getExampleData(groupId: String? = null): List<GroceryList> {
    // TODO: groupId is an example, should be implemented once the database is realised.
    return listOf(
        GroceryList(
            "test1", 123, "Mikal",
        ),
        GroceryList(
            "test2", 123, "Mikal",

        ),
        GroceryList(
            "test3", 123456789, "Mikal",

        )
    )
}