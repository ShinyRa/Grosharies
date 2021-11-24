package com.example.grosharies.data.GroceryList

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryListViewModel(application: Application) : AndroidViewModel(application) {
    val getAllGroceryLists: LiveData<List<GroceryList>>
    private val repository: GroceryListRepository

    val mutableListItems: MutableLiveData<String> = MutableLiveData()
    val listItems: LiveData<List<GroceryList>>

    init {
        val groceryListDao = GroshariesRoomDatabase.getDatabase(application)!!.groceryListDao()
        repository = GroceryListRepository(groceryListDao)
        getAllGroceryLists = repository.getAllGroceryLists

        listItems = Transformations.switchMap(mutableListItems) { param ->
            repository.getGroceryLists(param)
        }
    }

    fun getListItemsByGroup(groupId: String) {
        mutableListItems.value = groupId
    }

    fun insertGroceryLists(groceryList: GroceryList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGroceryLists(groceryList)
        }
    }

    fun updateGroceryLists(groceryList: GroceryList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGroceryLists(groceryList)
        }
    }

    fun deleteGroceryLists(groceryList: GroceryList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroceryLists(groceryList)
        }
    }

    class GroceryListViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(GroceryListViewModel::class.java)) {
                return GroceryListViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}