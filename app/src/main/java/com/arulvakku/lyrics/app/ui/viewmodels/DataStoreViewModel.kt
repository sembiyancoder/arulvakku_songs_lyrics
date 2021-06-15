package com.arulvakku.lyrics.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arulvakku.lyrics.app.preferences.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel
@Inject
constructor(private val preferenceStorage: PreferenceStorage) :
    ViewModel() {

    //saved key as liveData

    val userClosedGlobalInfoKey = preferenceStorage.isUserClosedGlobalInfoKey().asLiveData()
    val userMessage = preferenceStorage.getGlobalMessage().asLiveData()

    fun setUserClosedGlobalInfoKey(key: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceStorage.setUserClosedGlobalInfoKey(key)
        }
    }

    fun setUserGlobalMessage(key: String) {
        viewModelScope.launch {
            preferenceStorage.setGlobalMessage(key)
        }
    }

}