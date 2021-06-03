package com.arulvakku.lyrics.app.ui.home.song.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
class SongCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="sSongId")
    var sSongId: Int?,

    @ColumnInfo(name = "sCategory")
    var sCategory: String?,

    @ColumnInfo(name = "sCategoryId")
    var sCategoryId: Int?,

    @ColumnInfo(name = "sSong")
    var sSong: String?,

    @ColumnInfo(name = "sTitle")
    var sTitle: String?,
)