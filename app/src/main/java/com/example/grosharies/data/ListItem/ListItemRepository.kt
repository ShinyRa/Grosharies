package com.example.grosharies.data.ListItem

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.grosharies.data.GroshariesRoomDatabase

class ListItemRepository(context: Context) {
    private val listItemDao: ListItemDao

    init {
        val listItemDatabase = GroshariesRoomDatabase.getDatabase(context)
        listItemDao = listItemDatabase!!.listItemDao()
    }

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