package com.arulvakku.lyrics.app.ui.listeners

import com.arulvakku.lyrics.app.ui.view.home.model.SongCategoryModel

interface CellClickListenerCategory {
    fun onCategoryItemClickListener(item: SongCategoryModel, position:Int)
}