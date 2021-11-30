package com.example.grosharies.data.Group

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroupDao {

    @Query("SELECT * FROM `Group`")
    fun getGroups(): LiveData<List<Group>>

    @Query("SELECT * FROM `Group` WHERE id = :id")
    fun getGroupById(id: Int): LiveData<Group>

    @Insert
    suspend fun insertGroup(group: Group)

    @Update
    suspend fun updateGroup(group: Group)

    @Delete
    suspend fun deleteGroup(group: Group)
}