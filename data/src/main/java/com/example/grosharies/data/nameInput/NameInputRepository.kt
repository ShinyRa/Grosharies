package com.example.grosharies.data.nameInput

class NameInputRepository(private val nameInputDao: NameInputDao) {

    fun getNameInput(): NameInput? {
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

    fun ifUserExists(): NameInput? {
        return nameInputDao.getNameInput()
    }
}