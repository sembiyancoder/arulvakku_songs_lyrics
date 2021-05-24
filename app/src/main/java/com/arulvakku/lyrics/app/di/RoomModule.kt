package com.arulvakku.lyrics.app.di

import android.content.Context
import androidx.room.Room
import com.arulvakku.lyrics.app.database.AppDatabase
import com.arulvakku.lyrics.app.ui.song.cache.dao.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Create reference for database and it's dao
 */
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    /**
     * Create database [AppDatabase]
     */
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Create reference for [SongDao]
     */
    @Singleton
    @Provides
    fun provideSongDAO(database: AppDatabase): SongDao {
        return database.songDao()
    }


}