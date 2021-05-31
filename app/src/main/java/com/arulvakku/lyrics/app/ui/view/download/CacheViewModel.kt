package com.arulvakku.lyrics.app.ui.view.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arulvakku.lyrics.app.data.repository.MainRepository
import com.arulvakku.lyrics.app.ui.view.home.category.network.SongCategoryNetworkEntity
import com.arulvakku.lyrics.app.ui.view.home.song.network.SongNetworkEntity
import com.arulvakku.lyrics.app.utilities.NetworkHelper
import com.arulvakku.lyrics.app.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CacheViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val songCategoriesResult: MutableLiveData<Resource<SongCategoryNetworkEntity>> = MutableLiveData()
    val songsResult: MutableLiveData<Resource<SongNetworkEntity>> = MutableLiveData()

    init {
        getSongCategories()
        getSongs()
    }

    private fun getSongCategories() {
        viewModelScope.launch {
            songCategoriesResult.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getSongCategories().let {
                    if (it.isSuccessful) {
                        songCategoriesResult.postValue(Resource.success(it.body()))
                    } else songCategoriesResult.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else songCategoriesResult.postValue(Resource.error("No internet connection", null))
        }
    }

    private fun getSongs() {
        viewModelScope.launch {
            songsResult.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getSongs().let {
                    if (it.isSuccessful) {
                        songsResult.postValue(Resource.success(it.body()))
                    } else songsResult.postValue(
                        Resource.error(
                            it.errorBody().toString(),
                            null
                        )
                    )
                }
            } else songsResult.postValue(Resource.error("No internet connection", null))
        }
    }

}


