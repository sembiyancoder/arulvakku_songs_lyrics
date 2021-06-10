package com.arulvakku.lyrics.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryCacheEntity
import com.arulvakku.lyrics.app.ui.view.home.category.cache.SongCategoryDao
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongCacheEntity
import com.arulvakku.lyrics.app.ui.view.home.song.cache.SongDao
import com.arulvakku.lyrics.app.ui.view.library.cache.Playlist
import com.arulvakku.lyrics.app.ui.view.library.cache.PlaylistSongCrossRef
import com.arulvakku.lyrics.app.utilities.DATABASE_NAME


@Database(
    entities = [SongCategoryCacheEntity::class, SongCacheEntity::class, PlaylistSongCrossRef::class, Playlist::class], version = 1, exportSchema = false
)

abstract class ArulvakkuDatabase : RoomDatabase() {

    abstract fun songCategoryDao(): SongCategoryDao
    abstract fun songDao(): SongDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: ArulvakkuDatabase? = null

        fun getInstance(context: Context): ArulvakkuDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ArulvakkuDatabase {
            return Room.databaseBuilder(context, ArulvakkuDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            /* val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                             WorkManager.getInstance(context).enqueue(request)*/
                        }
                    }
                )
                .build()
        }
    }
}