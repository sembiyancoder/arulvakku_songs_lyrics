package com.arulvakku.lyrics.app.ui.song

import androidx.lifecycle.*
import com.arulvakku.lyrics.app.ui.song.model.Song
import com.arulvakku.lyrics.app.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SongViewModel
@Inject
constructor(
    private val repository: SongRepository,
) : ViewModel() {

    private val _dataStateSongs: MutableLiveData<Resource<List<Song>>> = MutableLiveData()

    val dataStateSongs: LiveData<Resource<List<Song>>>
        get() = _dataStateSongs

    fun setStateSongs() {
        /**
         * [CoroutineScope] tied to this [ViewModel].
         * This scope will be canceled when ViewModel will be cleared, i.e [ViewModel.onCleared] is called
         */
        viewModelScope.launch {
            repository.getAllSongs()
                /**
                 * Returns a flow that invokes the given [action] **before** each value of the upstream flow is emitted downstream.
                 */
                .onEach { dataState ->
                    _dataStateSongs.value = dataState
                }
                /**
                 * Terminal flow operator that [launches][launch] the [collection][collect] of the given flow in the [scope].
                 * It is a shorthand for `scope.launch { flow.collect() }`.
                 *
                 * Note that resulting value of [launchIn] is not used the provided scope takes care of cancellation.
                 */
                .launchIn(viewModelScope)
        }
    }
}


