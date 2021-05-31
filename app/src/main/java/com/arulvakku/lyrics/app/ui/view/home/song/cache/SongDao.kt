package com.arulvakku.lyrics.app.ui.view.home.song.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongList(entity: List<SongCacheEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongSingleItem(entity: SongCacheEntity)

    @Query("SELECT * FROM songs ORDER BY LOWER(sTitle) ASC")
    suspend fun getSongList(): List<SongCacheEntity>

    @Query("SELECT COUNT(*) from songs")
    suspend fun getSongCount(): Long

    @Query("SELECT * FROM songs WHERE sCategoryId = :id ORDER BY LOWER(sTitle) ASC")
    suspend fun getSongListByCategory(id: Int): List<SongCacheEntity>

}