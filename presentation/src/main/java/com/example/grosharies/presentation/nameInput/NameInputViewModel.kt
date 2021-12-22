package com.example.grosharies.presentation.nameInput

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.grosharies.data.GroshariesRoomDatabase
import com.example.grosharies.data.nameInput.NameInput
import com.example.grosharies.data.nameInput.NameInputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NameInputViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NameInputRepository

    val mutableListItems: MutableLiveData<String> = MutableLiveData()

    val username : MutableState<NameInput?> = mutableStateOf(null)
    init {
        val nameInputDao = GroshariesRoomDatabase.getDatabase(application)!!.nameInputDao()
        repository = NameInputRepository(nameInputDao)

        username.value = repository.ifUserExists()
    }

    fun getAllNameInputsByGroup(groupId: String) {
        mutableListItems.value = groupId
    }

    fun insertNameInput(nameInput: NameInput) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNameInput(nameInput)
            username.value = nameInput
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