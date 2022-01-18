package com.example.grosharies.presentation.groceryList

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import com.example.grosharies.data.groceryList.GroceryList
import com.example.grosharies.data.groceryList.GroceryListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GroceryListRepository

    private val mutableGroceryList: MutableLiveData<String> = MutableLiveData()
    val groceryLists: LiveData<List<GroceryList>>
    val groceryList: MutableState<GroceryList?> = mutableStateOf(null)

    /*
     * initialize the repository and the groceryLists
     */
    init {
        val groceryListDao = GroshariesRoomDatabase.getDatabase(application)!!.groceryListDao()
        repository = GroceryListRepository(groceryListDao)

        groceryLists = Transformations.switchMap(mutableGroceryList) { param ->
            if (param == "null") {
                repository.getGroceryListsWithoutGroup()
            } else {
                repository.getGroceryLists(param)
            }
        }
    }

    fun getListItemsByGroup(groupId: String) {
        mutableGroceryList.value = groupId
    }

    fun getListItemsWithoutGroup() {
        mutableGroceryList.value = "null"
    }

    fun getGroceryListById(id: Int) {
        groceryList.value = repository.getGroceryListById(id)
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