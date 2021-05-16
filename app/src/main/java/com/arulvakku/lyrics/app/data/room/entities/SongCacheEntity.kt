package com.arulvakku.lyrics.app.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongCacheEntity(
    @PrimaryKey
    val songId: Long,
    val title: String,
    val category: String,
    val categoryId: Long,
    val song: String
)
