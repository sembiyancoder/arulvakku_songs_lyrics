package com.arulvakku.lyrics.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arulvakku.lyrics.app.ui.song.cache.dao.SongDao
import com.arulvakku.lyrics.app.ui.song.cache.entities.SongCacheEntity

@Database(entities = [SongCacheEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    companion object {
        val DATABASE_NAME: String = "arulvakku_songs_lyrics"
    }


}