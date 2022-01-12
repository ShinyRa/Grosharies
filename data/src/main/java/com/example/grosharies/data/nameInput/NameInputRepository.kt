package com.example.grosharies.data.nameInput

class NameInputRepository(private val nameInputDao: NameInputDao) {

    fun getNameInput(): NameInput? {
        return nameInputDao.getNameInput()
    }

    suspend fun insertNameInput(nameInput: NameInput) {
        nameInputDao.insertNameInput(nameInput)
    }

    //checks if the user exists
    fun ifUserExists(): NameInput? {
        return nameInputDao.getNameInput()
    }
//    suspend fun updateNameInput(nameInput: NameInput) {
//        nameInputDao.updateNameInput(nameInput)
//    }
//
//    suspend fun deleteNameInput(nameInput: NameInput) {
//        nameInputDao.deleteNameInput(nameInput)
//    }
}