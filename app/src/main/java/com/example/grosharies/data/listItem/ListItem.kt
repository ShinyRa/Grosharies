package com.example.grosharies.data.listItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.grosharies.data.groceryList.GroceryList

@Entity(
    tableName = "ListItem",
    foreignKeys = [ForeignKey(
        entity = GroceryList::class,
        parentColumns = ["id"],
        childColumns = ["listId"]
    )]
)
data class ListItem(

    @ColumnInfo(name = "itemName")
    var itemName: String,
    @ColumnInfo(name = "itemAmount")
    var itemAmount: String,
    @ColumnInfo(name = "itemPurchased")
    val itemPurchased: Boolean,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "listId", index = true)
    var listId: Long? = null,

    )