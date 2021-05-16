package com.arulvakku.lyrics.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arulvakku.lyrics.app.data.room.dao.SongDao
import com.arulvakku.lyrics.app.data.room.entities.SongCacheEntity

@Database(entities = [SongCacheEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {
        val DATABASE_NAME: String = "arulvakku_songs_lyrics"
    }


}