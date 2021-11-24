package com.example.grosharies.data.GroceryList

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryListDao {

    @Query("SELECT * FROM `GroceryList`")
    fun getGroceryLists(): LiveData<List<GroceryList>>

    @Insert
    suspend fun insertGroceryLists(groceryList: GroceryList)

    @Update
    suspend fun updateGroceryLists(groceryList: GroceryList)

    @Delete
    suspend fun deleteGroceryLists(groceryList: GroceryList)
}