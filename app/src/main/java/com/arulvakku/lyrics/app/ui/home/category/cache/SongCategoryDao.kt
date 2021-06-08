package com.arulvakku.lyrics.app.ui.home.category.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongCategoryList(entity: List<SongCategoryCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongCategorySingleItem(entity: SongCategoryCacheEntity)

    @Query("SELECT * FROM song_category")
    suspend fun getSongCategoryList(): List<SongCategoryCacheEntity>

    @Query("SELECT COUNT(*) from song_category")
    suspend fun getCategoryCount(): Long
}
