package com.example.grosharies.data.group


class GroupRepository(private val groupDao: GroupDao) {

    fun getGroups(): MutableList<Group> {
        return groupDao.getGroups()
    }

    fun getGroupById(id: Int?): Group? {
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