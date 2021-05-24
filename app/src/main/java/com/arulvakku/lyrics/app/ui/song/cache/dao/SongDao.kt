package com.arulvakku.lyrics.app.ui.song.cache.dao

import androidx.room.*
import com.arulvakku.lyrics.app.ui.song.cache.entities.SongCacheEntity


@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SongCacheEntity): Long

    @Update
    suspend fun update(data: SongCacheEntity)

    @Delete
    suspend fun delete(data: SongCacheEntity)

    @Query("SELECT * FROM tbl_song")
    suspend fun getAllSongs(): List<SongCacheEntity>

    @Query("SELECT COUNT(song_id) FROM tbl_song")
    suspend fun getRowCount(): Int

    @Query("DELETE FROM tbl_song")
    suspend fun deleteAll()
}