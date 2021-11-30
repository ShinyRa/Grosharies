package com.example.grosharies.data.Group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.grosharies.data.GroceryList.GroceryList

@Entity(
    tableName = "Group",
)
data class Group(
    @ColumnInfo(name = "name")
    var name: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
)