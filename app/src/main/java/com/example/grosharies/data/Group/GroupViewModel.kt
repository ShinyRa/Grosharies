package com.example.grosharies.data.Group

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {
    val getAllGroceryLists: LiveData<List<Group>>
    private val repository: GroupRepository

    init {
        val groupDao = GroshariesRoomDatabase.getDatabase(application)!!.groupDao()
        repository = GroupRepository(groupDao)
        getAllGroceryLists = repository.getAllGroups
    }

    fun insertGroceryLists(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGroup(group)
        }
    }

    fun updateGroceryLists(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGroup(group)
        }
    }

    fun deleteGroceryLists(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(group)
        }
    }

    class GroceryListViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(GroupViewModel::class.java)) {
                return GroupViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}