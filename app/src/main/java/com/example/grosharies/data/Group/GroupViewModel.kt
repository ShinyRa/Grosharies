package com.example.grosharies.data.Group

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GroupRepository;

    val groups: MutableState<MutableList<Group>> = mutableStateOf(mutableListOf())
    val group: MutableState<Group> = mutableStateOf(Group(name = ""))

    init {
        val groupDao = GroshariesRoomDatabase.getDatabase(application)!!.groupDao()
        repository = GroupRepository(groupDao = groupDao)
        groups.value = repository.getGroups()
    }

    fun getGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            groups.value = repository.getGroups()
        }
    }

    fun getGroupById(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            group.value = repository.getGroupById(id) ?: Group(name = "")
        }
    }
    
    fun insertGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGroup(group)
            getGroups()
        }
    }

    fun updateGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGroup(group)
            getGroups()
        }
    }

    fun deleteGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(group)
            getGroups()
        }
    }

    class GroupViewModelFactory(
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