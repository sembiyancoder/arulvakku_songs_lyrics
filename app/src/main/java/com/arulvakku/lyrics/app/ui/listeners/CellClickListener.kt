package com.arulvakku.lyrics.app.ui.listeners


import com.arulvakku.lyrics.app.ui.view.home.category.SongCategoryModel
import com.arulvakku.lyrics.app.ui.view.home.song.SongModel

interface CellClickListener {
    fun onCategoryItemClickListener(item: SongCategoryModel)
    fun onSongCellClickListener(item: SongModel)
    fun onSongCellClickListenerWithPosition(item: SongModel, position:Int)
}