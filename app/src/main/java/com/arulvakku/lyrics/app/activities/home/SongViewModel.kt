package com.arulvakku.lyrics.app.activities.home

import androidx.lifecycle.*
import com.arulvakku.lyrics.app.utilities.Resource
import com.bosco.mrroom.utils.MainStateEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author bsoft-61 on 15/2/21.
 * */
@HiltViewModel
class SongViewModel
@Inject
constructor(
    private val repository: SongRepository,

    ) : ViewModel() {

    private val _dataStateSongs: MutableLiveData<Resource<List<Song>>> = MutableLiveData()

    val dataStateSongs: LiveData<Resource<List<Song>>>
        get() = _dataStateSongs

    fun setStateSongs(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogsEvent -> {
                    repository.getAllSongs()
                        .onEach { dataState ->
                            _dataStateSongs.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }
}

