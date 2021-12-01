package com.example.grosharies.data.ListItem

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.grosharies.data.GroshariesRoomDatabase
import com.example.grosharies.data.Group.Group
import com.example.grosharies.data.Group.GroupDao

class ListItemRepository(private val listItemDao: ListItemDao) {
    val getAllListItems: LiveData<List<ListItem>> = listItemDao.getAllListItems()

    fun getAllListItems(): LiveData<List<ListItem>> {
        return listItemDao.getAllListItems()
    }

    fun getListItemsFromList(groceryListId: String): LiveData<List<ListItem>> {
        return listItemDao.getListItemsFromList(groceryListId)
    }

    fun getListItem(listId: String): LiveData<ListItem> {
        return listItemDao.getListItem(listId)
    }

    suspend fun insertListItem(listItem: ListItem) {
        listItemDao.insertListItem(listItem)
    }

    suspend fun updateListItem(listItem: ListItem) {
        listItemDao.updateListItem(listItem)
    }

    suspend fun deleteListItem(listItem: ListItem) {
        listItemDao.deleteListItem(listItem)
    }

}