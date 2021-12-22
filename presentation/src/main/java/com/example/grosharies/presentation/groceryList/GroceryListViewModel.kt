package com.example.grosharies.data.GroceryList

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

    val mutableGroceryList: MutableLiveData<String> = MutableLiveData()
    val GroceryLists: LiveData<List<GroceryList>>
    val groceryList: MutableState<GroceryList?> = mutableStateOf(null)

//    val groceryList: LiveData<GroceryList>

    init {
        val groceryListDao = GroshariesRoomDatabase.getDatabase(application)!!.groceryListDao()
        repository = GroceryListRepository(groceryListDao)

        GroceryLists = Transformations.switchMap(mutableGroceryList) { param ->
            if (param == "null") {
                repository.getGroceryListsWithoutGroup()
            } else {
                repository.getGroceryLists(param)
            }
        }

//        groceryList = Transformations.switchMap(mutableGroceryList) { param ->
//            repository.getGroceryListById(param)
//        }
    }

    fun getListItemById(groupId: String) {
        mutableGroceryList.value = groupId
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