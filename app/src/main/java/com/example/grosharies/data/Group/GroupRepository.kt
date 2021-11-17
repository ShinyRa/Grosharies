package com.example.grosharies.data.Group

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.grosharies.data.GroshariesRoomDatabase

class GroupRepository(context: Context) {
    private var groupDao: GroupDao

    init {
        val groupRoomDatabase = GroshariesRoomDatabase.getDatabase(context)
        groupDao = groupRoomDatabase!!.groupDao()
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