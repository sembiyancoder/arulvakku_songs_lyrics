package com.arulvakku.lyrics.app.ui.view.home.song.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
class SongCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="song_id")
    var sSongId: Int?,

    @ColumnInfo(name = "category")
    var sCategory: String?,

    @ColumnInfo(name = "category_id")
    var sCategoryId: Int?,

    @ColumnInfo(name = "song")
    var sSong: String?,

    @ColumnInfo(name = "title")
    var sTitle: String?,
)