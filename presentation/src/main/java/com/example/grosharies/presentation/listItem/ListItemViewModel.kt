package com.example.grosharies.presentation.listItem

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import com.example.grosharies.data.group.Group
import com.example.grosharies.data.listItem.ListItem
import com.example.grosharies.data.listItem.ListItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ListItemRepository

    @SuppressLint("MutableCollectionMutableState")
    var listItems: MutableState<MutableList<ListItem>> = mutableStateOf(mutableListOf())
    val listItem: MutableState<ListItem> =
        mutableStateOf(ListItem(itemName = "", itemAmount = "1", itemPurchased = false))

    init {
        val listItemDao = GroshariesRoomDatabase.getDatabase(application)!!.listItemDao()
        repository = ListItemRepository(listItemDao = listItemDao)
    }

    fun getListItemById(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            listItem.value = repository.getListItemById(id) ?: ListItem(itemName = "", itemAmount = "1", itemPurchased = false)
        }
    }

    fun getListItemsByListId(listId: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            listItems.value = repository.getListItemByListId(listId)
        }
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