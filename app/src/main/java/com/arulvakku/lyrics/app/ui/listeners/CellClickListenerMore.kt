package com.arulvakku.lyrics.app.ui.listeners

import com.arulvakku.lyrics.app.ui.view.more.model.MoreData

interface CellClickListenerMore {
    fun onMoreItemClickListener(item: MoreData, position: Int)
}