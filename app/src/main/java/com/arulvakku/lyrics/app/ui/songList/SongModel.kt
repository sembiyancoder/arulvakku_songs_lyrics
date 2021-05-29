package com.arulvakku.lyrics.app.ui.songList

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class SongModel(
    var sSongId: Int?,
    var sCategory: String?,
    var sCategoryId: Int?,
    var sSong: String?,
    var sTitle: String?,
)