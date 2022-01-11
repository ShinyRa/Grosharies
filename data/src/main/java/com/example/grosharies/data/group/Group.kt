package com.example.grosharies.data.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Group",
)
data class Group(
    @ColumnInfo(name = "name")
    var name: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
)