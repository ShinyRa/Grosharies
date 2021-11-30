package com.example.grosharies.data.NameInput

import androidx.lifecycle.LiveData

class NameInputRepository(private val nameInputDao: NameInputDao) {

    val getAllNameInput: LiveData<List<NameInput>> = nameInputDao.getAllNameInput()

    fun getNameInput(): LiveData<List<NameInput>> {
        return nameInputDao.getNameInput()
    }

    suspend fun insertNameInput(nameInput: NameInput) {
        nameInputDao.insertNameInput(nameInput)
    }

    suspend fun updateNameInput(nameInput: NameInput) {
        nameInputDao.updateNameInput(nameInput)
    }

    suspend fun deleteNameInput(nameInput: NameInput) {
        nameInputDao.deleteNameInput(nameInput)
    }

}