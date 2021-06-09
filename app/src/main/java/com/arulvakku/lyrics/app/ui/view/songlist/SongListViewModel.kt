package com.arulvakku.lyrics.app.ui.view.songlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arulvakku.lyrics.app.data.repository.DatabaseRepository
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.utilities.Count
import com.arulvakku.lyrics.app.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject
constructor(private val repository: DatabaseRepository) : ViewModel() {

    val countResult: MutableLiveData<Resource<Count>> = MutableLiveData()
    val songsResult: MutableLiveData<Resource<List<SongModel>>> = MutableLiveData()

    fun getSongsListByCategory(categoryId: Int) {
        viewModelScope.launch {
            songsResult.postValue(Resource.loading(null))
            val data = repository.getSongsByCategory(categoryId)
            try {
                songsResult.postValue(
                    Resource.success(
                        data
                    )
                )
            } catch (e: Exception) {
                countResult.postValue(Resource.error("Something went wrong", null))
            }
        }
    }

}