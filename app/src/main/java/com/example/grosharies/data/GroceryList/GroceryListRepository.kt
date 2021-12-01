package com.example.grosharies.data.GroceryList

import androidx.lifecycle.LiveData

class GroceryListRepository(private val groceryListDao: GroceryListDao) {

    val getAllGroceryLists: LiveData<List<GroceryList>> = groceryListDao.getAllGroceryLists()

    fun getGroceryListById(listId: String): LiveData<GroceryList> {
        return groceryListDao.getGroceryListById(listId)
    }

    fun getGroceryLists(groupId: String): LiveData<List<GroceryList>> {
        return groceryListDao.getGroceryLists(groupId)
    }

    fun getGroceryListsWithoutGroup(): LiveData<List<GroceryList>> {
        return groceryListDao.getGroceryListsWithoutGroup()
    }

    suspend fun insertGroceryLists(groceryList: GroceryList) {
        groceryListDao.insertGroceryLists(groceryList)
    }

    suspend fun updateGroceryLists(groceryList: GroceryList) {
        groceryListDao.updateGroceryLists(groceryList)
    }

    suspend fun deleteGroceryLists(groceryList: GroceryList) {
        groceryListDao.deleteGroceryLists(groceryList)
    }

}