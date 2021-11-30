package com.example.grosharies.data.Group

import androidx.lifecycle.LiveData

class GroupRepository(private val groupDao: GroupDao) {

    val getAllGroups: LiveData<List<Group>> = groupDao.getGroups()


    fun getGroups(): LiveData<List<Group>> {
        return groupDao.getGroups()
    }

    fun getGroupById(id: Int): LiveData<Group> {
        return groupDao.getGroupById(id)
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