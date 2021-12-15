package com.example.grosharies.presentation.listItem

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.data.listItem.ListItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListItemViewModel(application: Application) : AndroidViewModel(application) {
    val getAllGroceryLists: LiveData<List<ListItem>>
    private val repository: ListItemRepository

    val mutableListItems: MutableLiveData<String> = MutableLiveData()
    val listItems: LiveData<List<ListItem>>

    val listItem: LiveData<ListItem>

    init {
        val listItemDao = GroshariesRoomDatabase.getDatabase(application)!!.listItemDao()
        repository = ListItemRepository(listItemDao)
        getAllGroceryLists = repository.getAllListItems()

        listItems = Transformations.switchMap(mutableListItems) { param ->
            repository.getListItemsFromList(param)
        }

        listItem = Transformations.switchMap(mutableListItems) { param ->
            repository.getListItem(param)
        }
    }

    fun getListItemsByList(groupId: String) {
        mutableListItems.value = groupId
    }

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