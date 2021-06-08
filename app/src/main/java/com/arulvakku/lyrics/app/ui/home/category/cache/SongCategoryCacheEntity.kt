package com.arulvakku.lyrics.app.ui.home.category.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_category")
data class SongCategoryCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "sCategoryId")
    var sCategoryId: Int,

    @ColumnInfo(name = "sCategoryName")
    var sCategoryName: String?,

    @ColumnInfo(name = "sColorCode")
    var sColorCode: String?,

    @ColumnInfo(name = "sCount")
    var sCount: Int?,
)