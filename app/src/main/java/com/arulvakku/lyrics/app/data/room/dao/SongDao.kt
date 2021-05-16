package com.arulvakku.lyrics.app.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arulvakku.lyrics.app.data.room.entities.SongCacheEntity


/**
 * @author bsoft-61 on 13/3/21.
 * */
@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SongCacheEntity): Long

    @Update
    suspend fun update(data: SongCacheEntity)

    @Delete
    suspend fun delete(data: SongCacheEntity)

    @Query("SELECT * FROM SongCacheEntity")
    suspend fun getAllSongs(): List<SongCacheEntity>

    @Query("SELECT COUNT(songId) FROM SongCacheEntity")
    suspend fun getRowCount(): Int

    @Query("DELETE FROM SongCacheEntity")
    suspend fun deleteAll()
}