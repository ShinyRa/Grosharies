package com.example.grosharies.data.NameInput

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.grosharies.data.ListItem.ListItem

@Entity(tableName = "NameInput")
data class NameInput(

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)
