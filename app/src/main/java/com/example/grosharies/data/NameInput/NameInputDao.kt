package com.example.grosharies.data.NameInput

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NameInputDao {

    @Query("SELECT * FROM `NameInput`")
    fun getAllNameInputs(): LiveData<List<NameInput>>

    @Query("SELECT * FROM `NameInput` limit 1")
    fun getNameInput(): LiveData<List<NameInput>>

    @Query("SELECT COUNT(*) FROM `NameInput`")
    fun ifUserExists(): Int

    @Insert
    suspend fun insertNameInput(nameInput: NameInput)

    @Update
    suspend fun updateNameInput(nameInput: NameInput)

    @Delete
    suspend fun deleteNameInput(nameInput: NameInput)
}