package com.example.grosharies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list")
data class List(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "listName")
    val listName: String,
    @ColumnInfo(name = "lastEdited")
    val lastEdited: Long,
    @ColumnInfo(name = "createdBy")
    val createdBy: String,
    @ColumnInfo(name = "listItems")
    val listItems: ArrayList<ListItems>,
) {
}