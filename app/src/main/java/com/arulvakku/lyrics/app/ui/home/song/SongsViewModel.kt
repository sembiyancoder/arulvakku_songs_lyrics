package com.arulvakku.lyrics.app.ui.home.song

import androidx.lifecycle.ViewModel
import com.arulvakku.lyrics.app.data.model.Song
import com.arulvakku.lyrics.app.utilities.getSongs

class SongsViewModel : ViewModel() {

    lateinit var songsResult: Song

    init {
        getSongCategories()
    }

    private fun getSongCategories() {
        songsResult = getSongs();
    }

}