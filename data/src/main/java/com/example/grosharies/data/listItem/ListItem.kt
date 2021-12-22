package com.example.grosharies.data.listItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.SET_NULL
import androidx.room.PrimaryKey
import com.example.grosharies.data.groceryList.GroceryList

@Entity(
    tableName = "ListItem",
    foreignKeys = [ForeignKey(
        entity = GroceryList::class,
        parentColumns = ["id"],
        childColumns = ["listId"],
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class ListItem(

    @ColumnInfo(name = "itemName")
    var itemName: String,
    @ColumnInfo(name = "itemAmount")
    var itemAmount: String,
    @ColumnInfo(name = "itemPurchased")
    var itemPurchased: Boolean,

    @ColumnInfo(name = "listId", index = true)
    var listId: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
)