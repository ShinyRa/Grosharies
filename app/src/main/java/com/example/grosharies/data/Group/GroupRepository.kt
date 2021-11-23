package com.example.grosharies.data.Group

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.grosharies.data.GroceryList.GroceryList
import com.example.grosharies.data.GroceryList.GroceryListDao
import com.example.grosharies.data.GroshariesRoomDatabase

class GroupRepository(private val groupDao: GroupDao) {

    val getAllGroups: LiveData<List<Group>> = groupDao.getGroups()

    fun getGroceryLists(): LiveData<List<Group>> {
        return groupDao.getGroups()
    }

    fun getGroups(): LiveData<List<Group>> {
        return groupDao.getGroups()
    }

    suspend fun insertGroup(group: Group) {
        groupDao.insertGroup(group)
    }

    suspend fun updateGroup(group: Group) {
        groupDao.updateGroup(group)
    }

    suspend fun deleteGroup(group: Group) {
        groupDao.deleteGroup(group)
    }
}