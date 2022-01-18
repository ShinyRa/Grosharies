package com.example.grosharies.data.nameInput

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NameInputDao {

    //get all the names
    @Query("SELECT * FROM `NameInput`")
    fun getAllNameInputs(): LiveData<List<NameInput>>

    //Gets the input from the names
    @Query("SELECT * FROM `NameInput` limit 1")
    fun getNameInput(): NameInput?

    @Query("SELECT COUNT(*) FROM `NameInput`")
    fun getAmountOfNames(): Int

    @Insert
    suspend fun insertNameInput(nameInput: NameInput)

    @Update
    suspend fun updateNameInput(nameInput: NameInput)

    @Delete
    suspend fun deleteNameInput(nameInput: NameInput)
}