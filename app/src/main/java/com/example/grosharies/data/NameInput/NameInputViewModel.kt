package com.example.grosharies.data.NameInput

import android.app.Application
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NameInputViewModel(application: Application) : AndroidViewModel(application) {
    val getAllNameInputs: LiveData<List<NameInput>>
    private val repository: NameInputRepository

    val mutableListItems: MutableLiveData<String> = MutableLiveData()
    val listItems: LiveData<List<NameInput>>

    init {
        val nameInputDao = GroshariesRoomDatabase.getDatabase(application)!!.nameInputDao()
        repository = NameInputRepository(nameInputDao)
        getAllNameInputs = repository.getAllNameInputs

        listItems = Transformations.switchMap(mutableListItems) { param ->
            repository.getNameInput()
        }
    }

    fun getAllNameInputsByGroup(groupId: String) {
        mutableListItems.value = groupId
    }

    fun insertNameInput(nameInput: NameInput) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNameInput(nameInput)
        }
    }

    fun updateNameInput(nameInput: NameInput) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNameInput(nameInput)
        }
    }

    fun deleteNameInput(nameInput: NameInput) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNameInput(nameInput)
        }
    }

    class NameInputViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(NameInputViewModel::class.java)) {
                return NameInputViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}