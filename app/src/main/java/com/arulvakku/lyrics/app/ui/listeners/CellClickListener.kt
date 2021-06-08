package com.arulvakku.lyrics.app.ui.listeners

import com.arulvakku.lyrics.app.ui.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.home.song.SongModel

interface CellClickListener {
    fun onCategoryItemClickListener(item: SongCategoryModel)
    fun onSongCellClickListener(item: SongModel)
}