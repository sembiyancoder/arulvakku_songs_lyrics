package com.arulvakku.lyrics.app.ui.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arulvakku.lyrics.app.datastore.UIModePreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(application: Application) : ViewModel() {

    // DataStore
    private val uiDataStore = UIModePreference(application)

    // get UI mode
    val getUIMode = uiDataStore.uiMode

    // save UI mode
    fun saveToDataStore(isNightMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            uiDataStore.saveToDataStore(isNightMode)
        }
    }

}