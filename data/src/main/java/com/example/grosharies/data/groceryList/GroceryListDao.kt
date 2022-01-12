package com.example.grosharies.data.groceryList

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryListDao {

    @Query("SELECT * FROM `GroceryList` g WHERE groupId = :groupId ORDER BY g.lastEdited DESC")
    fun getGroceryLists(groupId: String): LiveData<List<GroceryList>>

    @Query("SELECT * FROM `GroceryList` g WHERE groupId IS NULL ORDER BY g.lastEdited DESC")
    fun getGroceryListsWithoutGroup(): LiveData<List<GroceryList>>

    @Query("SELECT * FROM `GroceryList` WHERE id = :id")
    fun getGroceryListById(id: Int): GroceryList?

    @Insert
    suspend fun insertGroceryLists(groceryList: GroceryList)

    @Update
    suspend fun updateGroceryLists(groceryList: GroceryList)

    @Delete
    suspend fun deleteGroceryLists(groceryList: GroceryList)
}