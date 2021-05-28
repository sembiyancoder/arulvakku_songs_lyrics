package com.arulvakku.lyrics.app.ui.adapters

import com.arulvakku.lyrics.app.data.model.CategoriesResult
import com.arulvakku.lyrics.app.data.model.SongResult

interface CellClickListener {
    fun onCellClickListener(item: CategoriesResult)
    fun onSongCellClickListener(item: SongResult)
}