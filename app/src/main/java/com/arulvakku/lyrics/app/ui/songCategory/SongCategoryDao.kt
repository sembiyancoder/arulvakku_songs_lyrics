package com.arulvakku.lyrics.app.ui.songCategory

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
}
