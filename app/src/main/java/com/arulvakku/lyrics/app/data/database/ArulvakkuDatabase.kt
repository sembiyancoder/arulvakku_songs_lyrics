package com.arulvakku.lyrics.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryCacheEntity
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryDao
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongDao

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