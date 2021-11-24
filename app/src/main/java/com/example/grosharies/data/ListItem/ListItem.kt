package com.example.grosharies.data.ListItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.grosharies.data.GroceryList.GroceryList

@Entity(tableName = "ListItem", foreignKeys = [ForeignKey(entity = GroceryList::class, parentColumns = ["id"], childColumns = ["listId"])])
data class ListItem(

    @ColumnInfo(name = "itemName")
    val itemName: String,
    @ColumnInfo(name = "itemAmount")
    val itemAmount: Int,
    @ColumnInfo(name = "itemPurchased")
    val itemPurchased: Boolean,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "listId", index = true)
    val listId: Long? = null,

)