package com.example.grosharies.data.ListItem

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListItemViewModel(application: Application) : AndroidViewModel(application) {
    val getAllGroceryLists: LiveData<List<ListItem>>
    private val repository: ListItemRepository

    init {
        val listItemDao = GroshariesRoomDatabase.getDatabase(application)!!.listItemDao()
        repository = ListItemRepository(listItemDao)
        getAllGroceryLists = repository.getListItems()
    }

//    fun getListItemsByGroup(groupId: String) {
//        mutableListItems.value = groupId
//    }

    fun insertListItem(listItem: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertListItem(listItem)
        }
    }

    fun updateListItem(listItem: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateListItem(listItem)
        }
    }

    fun deleteListItem(listItem: ListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteListItem(listItem)
        }
    }

    class ListItemViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(ListItemViewModel::class.java)) {
                return ListItemViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}