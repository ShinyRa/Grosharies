package com.example.grosharies.data.listItem

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListItemDao {

    @Query("SELECT * FROM `ListItem` WHERE id = :id")
    fun getListItemById(id: Int?): ListItem?

    // TODO: Add created on property to order by
    @Query("SELECT * FROM `ListItem` li WHERE listId = :listId ORDER BY li.id DESC ")
    suspend fun getListItemsByListId(listId: Int?): MutableList<ListItem>

    @Insert
    suspend fun insertListItem(listItem: ListItem)

    @Update
    suspend fun updateListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)
}