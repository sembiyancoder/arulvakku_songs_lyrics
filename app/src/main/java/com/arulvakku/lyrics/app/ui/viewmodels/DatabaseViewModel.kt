package com.arulvakku.lyrics.app.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arulvakku.lyrics.app.data.repository.DatabaseRepository
import com.arulvakku.lyrics.app.ui.view.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel
import com.arulvakku.lyrics.app.utilities.Count
import com.arulvakku.lyrics.app.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel
@Inject
constructor(private val repository: DatabaseRepository) : ViewModel() {

    val countResult: MutableLiveData<Resource<Count>> = MutableLiveData()
    val songsResult: MutableLiveData<Resource<List<SongModel>>> = MutableLiveData()
    val categoriesResult: MutableLiveData<Resource<List<SongCategoryModel>>> = MutableLiveData()


    fun getSongCategoriesCount() {
        viewModelScope.launch {
            countResult.postValue(Resource.loading(null))
            val categoryCount = repository.getCategoryCount()
            val songCount = repository.getSongCount()
            try {
                countResult.postValue(
                    Resource.success(
                        Count(
                            songCount = songCount,
                            categoryCount = categoryCount
                        )
                    )
                )
            } catch (e: Exception) {
                countResult.postValue(Resource.error("Something went wrong", null))
            }
        }
    }

    fun getSongCategories() {
        viewModelScope.launch {
            categoriesResult.postValue(Resource.loading(null))
            val data = repository.getCategories()
            try {
                categoriesResult.postValue(
                    Resource.success(
                        data
                    )
                )
            } catch (e: Exception) {
                countResult.postValue(Resource.error("Something went wrong", null))
            }
        }
    }

    fun getSongs() {
        viewModelScope.launch {
            songsResult.postValue(Resource.loading(null))
            val data = repository.getSongs()
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