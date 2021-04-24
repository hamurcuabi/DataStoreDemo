package com.emrehmrc.pref_datastore

import android.app.Application
import androidx.lifecycle.*
import com.emrehmrc.pref_datastore.helper.PreferenceKeys
import com.emrehmrc.pref_datastore.repo.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          4/24/2021
 *  FileName     MainActivityViewModel
 */
class MainActivityViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val repository = DataStoreRepository(application)

    val readStringDataStore =
        repository.readStringFromDataStore(PreferenceKeys.MY_STRING)
            .asLiveData()

    val readBooleanDataStore =
        repository.readBooleanFromDataStore(PreferenceKeys.MY_BOOLEAN)
            .asLiveData()

    val readIntDataStore =
        repository.readIntegerFromDataStore(PreferenceKeys.MY_INT)
            .asLiveData()

    fun saveStringToDataStore(myName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveStringToDataStore(PreferenceKeys.MY_STRING, myName)
    }

    fun saveBooleanToDataStore(isChecked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveBooleanToDataStore(PreferenceKeys.MY_BOOLEAN, isChecked)
    }

    fun saveIntToDataStore(isChecked: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveBooleanToDataStore(PreferenceKeys.MY_BOOLEAN, isChecked)
    }
}

class MainActivityViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}