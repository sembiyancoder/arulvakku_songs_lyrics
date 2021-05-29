package com.arulvakku.lyrics.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryCacheEntity
import com.arulvakku.lyrics.app.ui.songCategory.SongCategoryDao
import com.arulvakku.lyrics.app.ui.songList.SongCacheEntity
import com.arulvakku.lyrics.app.ui.songList.SongDao

@Database(
    entities = arrayOf(
        SongCategoryCacheEntity::class,
        SongCacheEntity::class
    ), version = 1, exportSchema = false
)
abstract class ArulvakkuDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "arulvakku.db"
    }

    abstract fun songCategoryDao(): SongCategoryDao
    abstract fun songDao(): SongDao
}