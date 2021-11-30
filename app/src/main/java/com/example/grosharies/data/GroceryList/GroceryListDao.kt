package com.example.grosharies.data.GroceryList

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryListDao {

    @Query("SELECT * FROM `GroceryList`")
    fun getAllGroceryLists(): LiveData<List<GroceryList>>

    @Query("SELECT * FROM `GroceryList` WHERE groupId = :groupId")
    fun getGroceryLists(groupId: String): LiveData<List<GroceryList>>

    @Query("SELECT * FROM `GroceryList` WHERE groupId IS NULL")
    fun getGroceryListsWithoutGroup(): LiveData<List<GroceryList>>

    @Insert
    suspend fun insertGroceryLists(groceryList: GroceryList)

    @Update
    suspend fun updateGroceryLists(groceryList: GroceryList)

    @Delete
    suspend fun deleteGroceryLists(groceryList: GroceryList)
}