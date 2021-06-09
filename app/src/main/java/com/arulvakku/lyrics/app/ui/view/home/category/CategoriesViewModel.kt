package com.arulvakku.lyrics.app.ui.view.home.category

import androidx.lifecycle.ViewModel
import com.arulvakku.lyrics.app.data.model.SongCategory
import com.arulvakku.lyrics.app.utilities.getSongCategories

class CategoriesViewModel : ViewModel() {

    lateinit var songCategoriesResult: SongCategory

    init {
        getCategories()
    }

    private fun getCategories() {
        songCategoriesResult = getSongCategories();
    }

}