package com.example.grosharies.data.GroceryList

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.grosharies.data.GroshariesRoomDatabase

class GroceryListRepository(private val groceryListDao: GroceryListDao) {

    val getAllGroceryLists: LiveData<List<GroceryList>> = groceryListDao.getGroceryLists()

    fun getGroceryLists(): LiveData<List<GroceryList>> {
        return groceryListDao.getGroceryLists()
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