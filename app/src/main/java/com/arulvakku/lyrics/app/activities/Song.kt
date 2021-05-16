package com.arulvakku.lyrics.app.activities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Song(
    val songId: Long,
    val title: String,
    val category: String,
    val categoryId: Long,
    val song: String
)
