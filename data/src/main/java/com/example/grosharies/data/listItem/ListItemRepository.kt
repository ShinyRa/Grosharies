package com.example.grosharies.data.listItem

import androidx.lifecycle.LiveData

class ListItemRepository(private val listItemDao: ListItemDao) {
    val getAllListItems: LiveData<List<ListItem>> = listItemDao.getListItems()

    fun getListItems(): LiveData<List<ListItem>> {
        return listItemDao.getListItems()
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