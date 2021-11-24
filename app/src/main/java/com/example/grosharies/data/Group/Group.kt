package com.example.grosharies.data.Group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.grosharies.data.GroceryList.GroceryList

@Entity(
    tableName = "Group",
    foreignKeys = [ForeignKey(
        entity = GroceryList::class,
        parentColumns = ["id"],
        childColumns = ["groupId"]
    )]
)
data class Group(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "groupId")
    val groupId: Long? = null,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
)