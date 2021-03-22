package com.arulvakku.lyrics.app.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "song_category")
data class SongCategory(
    @PrimaryKey(autoGenerate = true) private val id: Int,
    @ColumnInfo(name = "category_name") private val category_name: String,
    @ColumnInfo(name = "count") private val count: String
)