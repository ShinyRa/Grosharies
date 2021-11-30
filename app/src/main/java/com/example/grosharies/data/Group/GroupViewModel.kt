package com.example.grosharies.data.Group

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {
    val getAllGroups: LiveData<List<Group>>
    private val repository: GroupRepository

    val groupId: MutableLiveData<Int> = MutableLiveData()
    val group: LiveData<Group>

    init {
        val groupDao = GroshariesRoomDatabase.getDatabase(application)!!.groupDao()
        repository = GroupRepository(groupDao)
        getAllGroups = repository.getAllGroups
        group = Transformations.switchMap(groupId) {
            repository.getGroupById(it)
        }
    }

    fun getGroups() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getGroups()
        }
    }

    fun getGroupById(id: Int) {
        groupId.value = id
    }

    fun insertGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGroup(group)
        }
    }

    fun updateGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGroup(group)
        }
    }

    fun deleteGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(group)
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