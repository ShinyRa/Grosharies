package com.example.grosharies.data.ListItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listItem")
data class ListItem(

    @ColumnInfo(name = "itemName")
    val itemName: String,
    @ColumnInfo(name = "itemAmount")
    val itemAmount: Int,
    @ColumnInfo(name = "itemPurchased")
    val itemPurchased: Boolean,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
) {
}