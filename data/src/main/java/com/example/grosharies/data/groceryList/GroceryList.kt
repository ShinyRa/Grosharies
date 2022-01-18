package com.example.grosharies.data.groceryList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.grosharies.data.group.Group
import java.util.*

@Entity(
    tableName = "GroceryList",
    foreignKeys = [ForeignKey(
        entity = Group::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class GroceryList(
    @ColumnInfo(name = "listName")
    var listName: String,
    @ColumnInfo(name = "lastEdited")
    val lastEdited: Date,
    @ColumnInfo(name = "createdBy")
    val createdBy: String,

    @ColumnInfo(name = "groupId")
    val groupId: Long? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)