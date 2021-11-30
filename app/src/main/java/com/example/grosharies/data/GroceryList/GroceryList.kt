package com.example.grosharies.data.GroceryList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "GroceryList",
    foreignKeys = [ForeignKey(
        entity = GroceryList::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = CASCADE
    )]
)
data class GroceryList(

    @ColumnInfo(name = "listName")
    val listName: String,
    @ColumnInfo(name = "lastEdited")
    val lastEdited: Date,
    @ColumnInfo(name = "createdBy")
    val createdBy: String,

    @ColumnInfo(name = "groupId")
    val groupId: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)