package com.example.grosharies.data.ListItem

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListItemDao {

    @Query("SELECT * FROM `ListItem`")
    fun getAllListItems(): LiveData<List<ListItem>>

    @Query("SELECT * FROM `ListItem` WHERE groceryListId = :groceryListId")
    fun getListItemsFromList(groceryListId: String): LiveData<List<ListItem>>

    @Query("SELECT * FROM `ListItem` WHERE id = :listId")
    fun getListItem(listId: String): LiveData<ListItem>

    @Insert
    suspend fun insertListItem(listItem: ListItem)

    @Update
    suspend fun updateListItem(listItem: ListItem)

    @Delete
    suspend fun deleteListItem(listItem: ListItem)
}