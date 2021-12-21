package com.example.grosharies.data.NameInput

import androidx.lifecycle.LiveData

class NameInputRepository(private val nameInputDao: NameInputDao) {

    val getAllNameInputs: LiveData<List<NameInput>> = nameInputDao.getAllNameInputs()

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

    fun ifUserExists(): Int {
        return nameInputDao.ifUserExists()
    }

}