package com.example.grosharies.data.listItem

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListItemDao {

    @Query("SELECT * FROM `ListItem` WHERE id = :id")
    fun getListItemById(id: Int?): ListItem?

    @Query("SELECT * FROM `ListItem` WHERE listId = :listId")
    suspend fun getListItemsByListId(listId: Int?): MutableList<ListItem>

    @Insert
    suspend fun insertListItem(listItem: ListItem)

    @Update
    suspend fun updateListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)
}