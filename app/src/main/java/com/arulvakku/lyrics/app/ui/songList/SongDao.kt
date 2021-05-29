package com.arulvakku.lyrics.app.ui.songList

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

    @Query("SELECT * FROM songs")
    suspend fun getSongList(): List<SongCacheEntity>
}